package com.examenp.recliclerview.logic.network.usercase

import com.examenp.recliclerview.data.network.entities.UserDB
import com.examenp.recliclerview.data.network.repositories.AutenticationRepository
import com.examenp.recliclerview.data.network.repositories.UsersRepository

class SaveUserInDBUserCase {
    suspend fun invoke(id: String, email: String, name: String): UserDB? {
        var us: UserDB? = null
        val a = UsersRepository().saveUserDB(id, email, name).getOrNull()
            /*.onSuccess {
                us = it
            }
            .onFailure {
                us = null
            }*/
        return a

    }
}