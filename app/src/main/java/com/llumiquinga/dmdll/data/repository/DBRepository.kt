package com.llumiquinga.dmdll.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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


class DBConnection(){
    fun getConnectionOPlarga(context:Context):DBRepository{
        val con= Room.databaseBuilder(context,
            DBRepository::class.java,
            "DBTest"
        ).build()

        return con
    }
    fun getConnection(context:Context):DBRepository= Room.databaseBuilder(context,
            DBRepository::class.java,
            "DBTest"
        ).build()

}