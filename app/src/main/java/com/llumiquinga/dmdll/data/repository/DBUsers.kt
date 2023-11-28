package com.llumiquinga.dmdll.data.repository

import com.llumiquinga.dmdll.data.entities.Users

class DBUsers {
    fun getListUsers (): List<Users> {

        var usr1=Users("danielusr","danielpss","asdf",1)
        var usr2=Users("pedrousr","pedropss","asdf",2)
        var usr3=Users("mariausr","mariapss","adf",3)
        var usr4=Users("123","123","asdf",4)



        val lstUsers= listOf<Users>(usr1,usr2,usr3,usr4)

        return lstUsers
    }



}