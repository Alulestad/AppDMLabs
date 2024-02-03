package com.examenp.recliclerview.ui.viewmodels

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.biometric.BiometricManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examenp.recliclerview.R

class LoginViewModel: ViewModel() {

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

}