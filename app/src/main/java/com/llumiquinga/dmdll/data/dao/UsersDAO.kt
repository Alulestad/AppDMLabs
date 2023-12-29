package com.llumiquinga.dmdll.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.llumiquinga.dmdll.data.entities.Users

@Dao
interface UsersDAO {

    @Query("select Users.userName,Users.password,Users.profile,Users.id,Users.firsName,Users.lastName from Users")
    fun getAllUsers():List<Users>

    @Query("select * from Users where id=:id")
    fun getUser(id:Int):Users

    @Insert
    fun insertUser(users:List<Users>)

    @Update
    fun updateUser(user:List<Users>)

    @Delete
    fun deleteUser(user:Users)

    //@Delete
    //public fun deleteAlbumAndSongs(val album: Album, val songs: List<Song>)



}