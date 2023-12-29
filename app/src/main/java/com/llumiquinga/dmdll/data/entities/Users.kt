package com.llumiquinga.dmdll.data.entities

import android.security.identity.AccessControlProfile
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Users (

    @ColumnInfo
    var userName:String,

    @ColumnInfo
    val password:String,

    @ColumnInfo
    var firsName:String,

    @ColumnInfo
    var lastName:String //estos datos se pueden acceder agregando .firstName="daniel"

    //var name:String="",
    //var apellido:String="",
    //var perfil: String?=null //admin, user
    ){

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo
    var id:Int=-1

    @ColumnInfo
    var profile:String?=""
    constructor(userName:String,password: String,profile: String? ,id:Int,firsName:String,lastName:String):this(userName,password,firsName,lastName){
        this.profile=profile
        this.id=id

    }

    //constructor(id:Int,userName:String,password: String,profile: String?,firsName:String, lastName:String): this(id,userName,password,profile,firsName,lastName)



}



//nombre apellido perfil