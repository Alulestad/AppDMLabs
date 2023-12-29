package com.llumiquinga.dmdll.logic.login

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.llumiquinga.dmdll.core.My_Applicacion
import com.llumiquinga.dmdll.data.entities.Users
import com.llumiquinga.dmdll.data.repository.DBRepository
import com.llumiquinga.dmdll.data.repository.DBUsers
import com.llumiquinga.dmdll.ui.core.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingIn (val connection_DBRpository:DBRepository){

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

        ///

        users.filter {
            it.lastName=="1"
        }
        ///
        if (lstUsers.isNotEmpty()){
            Log.d(Constants.TAG,"SingIn> checkUserAndPasswordForma4> "+lstUsers.toString()+" ID:"+lstUsers.first().id)

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
        connection_DBRpository.getUserDAO().getUser(userId)


     fun getUserName3(userId:Int):Users= DBUsers().getListUsers().first {
            it.id==userId
    }

      fun insertUser()=if(connection_DBRpository.getUserDAO().getAllUsers().isEmpty()){
        val a=DBUsers().getListUsers()
        connection_DBRpository.getUserDAO().insertUser(a)
    }else{
        null
    }

     fun getAllUsers():List<Users> {
         var todosUsiario=connection_DBRpository.getUserDAO().getAllUsers()
         Log.d(Constants.TAG,"SingIn> getAllUsers> "+todosUsiario.toString()+" ID:"+todosUsiario.first().id)
         return todosUsiario
     }

}