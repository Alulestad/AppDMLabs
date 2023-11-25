package com.llumiquinga.dmdll.data.entities

data class Users (
    var id:Int,
    var userName:String,
    var password:String
    )

data class Users_complete (
    var id:Int,
    var userName:String,
    var password:String,
    var nombre:String,
    var apellido:String,
    var perfil:String
)



//nombre apellido perfil