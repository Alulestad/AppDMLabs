package com.llumiquinga.dmdll.core

import android.app.Application
import com.llumiquinga.dmdll.data.repository.DBConnection
import com.llumiquinga.dmdll.data.repository.DBRepository
import com.llumiquinga.dmdll.logic.login.SingIn

class My_Applicacion :Application(){

    override fun onCreate() {
        super.onCreate()
        con=DBConnection().getConnection(applicationContext)
        //SingIn(con).insertUser()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    companion object {
        private lateinit var con:DBRepository
        fun getConnectionDB():DBRepository?{
            return con
        }
    }


}