package com.llumiquinga.dmdll.logic.usercase.local.login

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.llumiquinga.dmdll.core.My_Applicacion
import com.llumiquinga.dmdll.data.local.entities.Users
import com.llumiquinga.dmdll.data.local.repository.DBRepository
import com.llumiquinga.dmdll.data.local.repository.DBUsers
import com.llumiquinga.dmdll.ui.core.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

class SingIn (val connection_DBRpository: DBRepository){

    private val db: DBUsers = DBUsers()
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

        Log.d(Constants.TAG,"SingIn>checkUserAndPasswordForma4")

        //

        var users:List<Users>

        var lstUsers:List<Users> = ArrayList()
        runBlocking() {
            withContext(Dispatchers.IO){
                val listC=listOf(
                    async { connection_DBRpository.getUserDAO().getAllUsers() }
                )
                val w1= listC.awaitAll()
                users= w1[0]
                Log.d(Constants.TAG,"SingIn>checkUserAndPasswordForma4>GlobalScope>withContext>users: $users")


            }
        }

        Log.d(Constants.TAG,"SingIn>checkUserAndPasswordForma4>users: $users")
        lstUsers=users.filter {
            it.password==password && it.userName ==user

        }
        Log.d(Constants.TAG,"FINN DISPACHER ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOO!!!!!!!!!!!!!!!!")
        Log.d(Constants.TAG,"SingIn>checkUserAndPasswordForma4>lstUsers: $lstUsers")

        Log.d(Constants.TAG,"SingIn> checkUserAndPasswordForma4> lstUsers OUT"+lstUsers.toString())
        ///
        if (lstUsers.isNotEmpty()){
            Log.d(Constants.TAG,"ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOO!!!!!!!!!!!!!!!!")
            Log.d(Constants.TAG,"SingIn> checkUserAndPasswordForma4> "+lstUsers.toString()+" ID:"+lstUsers.first().id)

            return lstUsers.first().id
        }

        return -1
    }

     fun getUserName(userId:Int): Users {
        return DBUsers().getListUsers().filter {
            it.id==userId
        }.first()
    }
     fun getUserNamever2(userId:Int): Users { //##
        return DBUsers().getListUsers().first() {
            it.id==userId
        }
    }

     fun getUserName1(userId:Int): Users =
        connection_DBRpository.getUserDAO().getUser(userId)
    fun getUserName3anterior(userId:Int): Users = DBUsers().getListUsers().first {
        it.id==userId
    }

     fun getUserName3(userId:Int): Users {
         var user1:Users=Users("","","","")
         runBlocking() {
             withContext(Dispatchers.IO) {
                 user1 = connection_DBRpository.getUserDAO().getAllUsers().first {
                     it.id == userId
                 }
             }
         }
         return user1
     }


      fun insertUser()=if(connection_DBRpository.getUserDAO().getAllUsers().isEmpty()){
        val a= DBUsers().getListUsers()
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