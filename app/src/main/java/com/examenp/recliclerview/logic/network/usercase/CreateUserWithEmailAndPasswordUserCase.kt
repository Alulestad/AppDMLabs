package com.examenp.recliclerview.logic.network.usercase

import com.examenp.recliclerview.data.network.entities.UserDB
import com.examenp.recliclerview.data.network.repositories.AutenticationRepository

class CreateUserWithEmailAndPasswordUserCase {
    suspend fun invoke(email:String, password:String):UserDB?{
        var user: UserDB?=null
        AutenticationRepository().createUsersWithEmailAndPassword(email,password)
            .onSuccess {
                user =it
            }
            .onFailure {
                user=null
            }
        return user
    }
}