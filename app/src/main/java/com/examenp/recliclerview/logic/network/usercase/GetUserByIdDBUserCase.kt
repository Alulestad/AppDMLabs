package com.examenp.recliclerview.logic.network.usercase

import com.examenp.recliclerview.data.network.entities.UserDB
import com.examenp.recliclerview.data.network.repositories.UsersRepository

class GetUserByIdDBUserCase {
    suspend fun invoke(id: String): UserDB? {

        return UsersRepository().getUserByID(id).getOrNull()

    }
}