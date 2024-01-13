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
import kotlinx.coroutines.launch

class Registro (val connection_DBRpository: DBRepository){


    fun registrar(usuario: String, apellido: String, email: String, nombre: String, pass: String, pass2: String): Int {
        Log.d(Constants.TAG,"Registro>registrar")
        var a: Users= Users(usuario,pass,"",usuario.toInt(),nombre,apellido)

        GlobalScope.launch (Dispatchers.IO){

            connection_DBRpository.getUserDAO().insertOnlyUser(a);
        }
        Log.d(Constants.TAG,"Registro>registrar>a: $a ID: ${a.id}")


        return a.id
    }

}