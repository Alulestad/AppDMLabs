package com.llumiquinga.dmdll.logic.login

import com.llumiquinga.dmdll.data.repository.DBUsers

class SingIn {

    private val db:DBUsers= DBUsers()
    fun checkUserAndPassword(user:String, password:String): Boolean {

        val usuarioIngresado=user
        val contraseniaIngresada=password
        val listaUsrPss= db.getListUsers()

        for(elemento in listaUsrPss){
            if(elemento.userName== usuarioIngresado && elemento.password==contraseniaIngresada){
                return true
            }

        }


        return false

    }
}