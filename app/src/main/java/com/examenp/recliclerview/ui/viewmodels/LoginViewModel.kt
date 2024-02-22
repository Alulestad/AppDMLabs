package com.examenp.recliclerview.ui.viewmodels

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.biometric.BiometricManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examenp.recliclerview.R
import com.examenp.recliclerview.data.network.entities.UserDB
import com.examenp.recliclerview.logic.network.usercase.CreateUserWithEmailAndPasswordUserCase
import com.examenp.recliclerview.logic.network.usercase.SaveUserInDBUserCase
import com.examenp.recliclerview.logic.network.usercase.SingInUserWithEmailAndPasswordUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val user get() = _user
    private val _user = MutableLiveData<UserDB>()

    val error get()=_error
    private val _error = MutableLiveData<String>()


    val resultCheckBiometric = MutableLiveData<Int>() //voy hacer que devuelva los codigos

    fun checkBiometric(context:Context){ //checa el biometrico
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS ->{
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_SUCCESS )

            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE )


            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE )


            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> { //configuracion para mostrar la huella
                // Prompts the user to create credentials that your app accepts.
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED )


            }
        }
    }

    fun sigInUserWithEmailAndPassword(email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val us = SingInUserWithEmailAndPasswordUserCase().invoke(email,password)
            if(us!=null){
                _user.postValue(us!!)
            }else{
                _error.postValue("Ocurrio un error")

            }


        }
    }

    fun createUserWithEmailAndPassword(email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val us = CreateUserWithEmailAndPasswordUserCase().invoke(email,password)
            if(us!=null){
                val newUs=SaveUserInDBUserCase().invoke(us.id,us.email,"usName")
                _user.postValue(newUs!!)
            }else{
                _error.postValue("Ocurrio un error")

            }


        }
    }

}