package com.llumiquinga.dmdll.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.llumiquinga.dmdll.data.dao.UsersDAO
import com.llumiquinga.dmdll.data.entities.Users

@Database(
    entities = [Users::class],
    version = 1
)
abstract class DBRepository : RoomDatabase(){

    abstract fun getUserDAO():UsersDAO


}