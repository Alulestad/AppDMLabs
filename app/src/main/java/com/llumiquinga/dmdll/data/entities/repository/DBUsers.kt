package com.llumiquinga.dmdll.data.entities.repository

import com.llumiquinga.dmdll.data.entities.Users

class DBUsers {
    fun getListUsers (): List<Users> {

        var usr1=Users(1,"danii","13241")
        var usr2=Users(2,"danii2","13242")
        var usr3=Users(3,"danii3","13243")

        var lstUsers= listOf<Users>(usr1,usr2,usr3)

        return lstUsers
    }



}