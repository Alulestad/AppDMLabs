package com.llumiquinga.dmdll.data.repository

import com.llumiquinga.dmdll.data.entities.Users

class DBUsers {
    fun getListUsers (): List<Users> {

        var usr1=Users(1,"danielusr","danielpss")
        var usr2=Users(2,"pedrousr","pedropss","Pedro", "Rodrigez")
        var usr3=Users(3,"mariausr","mariapss")
        var usr4=Users(4,"123","123")

        var lstUsers= listOf<Users>(usr1,usr2,usr3,usr4)

        return lstUsers
    }



}