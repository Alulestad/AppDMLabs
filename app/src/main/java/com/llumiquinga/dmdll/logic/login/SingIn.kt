package com.llumiquinga.dmdll.logic.login

import android.content.Context
import android.util.Log
import com.llumiquinga.dmdll.data.entities.Users
import com.llumiquinga.dmdll.data.repository.DBConnection
import com.llumiquinga.dmdll.data.repository.DBRepository
import com.llumiquinga.dmdll.data.repository.DBUsers
import com.llumiquinga.dmdll.ui.core.Constants

class SingIn (val connection:DBRepository){

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

    fun checkUserAndPasswordForma2(user:String, password:String): Boolean {//COMPLETAR


        val users= DBUsers().getListUsers()
        users.forEach{
            it.userName==user && it.password == password
        }


        return false

    }

    fun checkUserAndPasswordForma3(user:String, password:String): Boolean { //COMPLETAR
        val users= DBUsers().getListUsers()
        val lstUsers=users.filter {
            it.password==password && it.userName ==user
        }

        Log.d(Constants.TAG,lstUsers.toString())
        return lstUsers.isNotEmpty()

    }

    fun checkUserAndPasswordForma4(user:String, password:String): Int { //COMPLETAR
        val users= DBUsers().getListUsers()
        val lstUsers=users.filter {
            it.password==password && it.userName ==user
        }


        if (lstUsers.isNotEmpty()){

            Log.d(Constants.TAG,lstUsers.toString()+" ID:"+lstUsers.first().id)
            return lstUsers.first().id
        }

        return -1
    }

     fun getUserName(userId:Int):Users{
        return DBUsers().getListUsers().filter {
            it.id==userId
        }.first()
    }
     fun getUserNamever2(userId:Int):Users{ //##
        return DBUsers().getListUsers().first() {
            it.id==userId
        }
    }

     fun getUserName1(userId:Int):Users=
        connection.getUserDAO().getUser(userId)


     fun getUserName3(userId:Int):Users= DBUsers().getListUsers().first {
            it.id==userId
    }

      fun insertUser()=if(connection.getUserDAO().getAllUsers().isEmpty()){
        val a=DBUsers().getListUsers()
        connection.getUserDAO().insertUser(a)
    }else{
        null
    }

     fun getAllUsers():List<Users> = connection.getUserDAO().getAllUsers()

}