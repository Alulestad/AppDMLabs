package com.llumiquinga.dmdll.data.entities

import android.security.identity.AccessControlProfile
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users (

    var userName:String,
    val password:String,
    //var name:String="",
    //var apellido:String="",
    //var perfil: String?=null //admin, user
    ){

    @PrimaryKey (autoGenerate = true)
    var id:Int=-1

    var firsName:String =""
    var lastName:String ="" //estos datos se pueden acceder agregando .firstName="daniel"
    var profile:String?=""
    constructor(userName:String,password: String,profile: String? ,id:Int):this(userName,password){
        this.profile=profile
        this.id=id
    }

    //constructor(id:Int,userName:String,password: String,profile: String?,firsName:String, lastName:String): this(id,userName,password,profile,firsName,lastName)
}



//nombre apellido perfil