package com.examenp.recliclerview.ui.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.examenp.recliclerview.R
import java.util.concurrent.Executor

class LoginActivity : AppCompatActivity() {
    private lateinit var btnFinger:ImageView
    private lateinit var txtInfo:TextView

    private lateinit var executor: Executor //permite ejecutar app en segundo plano
        //necesita hilos
    private lateinit var biometricPrompt: BiometricPrompt   // maja los eventos del biometrico
    private lateinit var promptInfo: BiometricPrompt.PromptInfo //es el dialogo, lo que se meustra

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initListener()
        checkBiometric()
        AutenticationVariables()
    }

    private fun initListener(){

        btnFinger=findViewById<ImageView>(R.id.imgFinger)
        txtInfo=findViewById(R.id.txtInfo)

        btnFinger.setOnClickListener{

            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun AutenticationVariables(){ //creo mis variables
        executor = ContextCompat.getMainExecutor(this)
        //la activiti principal va ejecutar esto,y permite trabajar en segundo plano

        biometricPrompt = BiometricPrompt(this, executor,//porque el biometric pront necesita ejecutarse en un segundo plano.
            object : BiometricPrompt.AuthenticationCallback() { //callback es un proceso en segundo plano y devuelve asincronicamente.
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Log.e("MY_APP_TAG", "Autentication falied")
                    //podria poner un snack bar que me ponga esto

                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.e("MY_APP_TAG", "Autentication error")

                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                }

            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()//patron builder
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setAllowedAuthenticators(Authenticators.BIOMETRIC_STRONG or Authenticators.DEVICE_CREDENTIAL) //aca yo podria especificar si quiero solo con la huella o en conjunto
            .setNegativeButtonText("Cancel") // entonces aca tengo la opcion de cancel
            .build()


    }

    private fun checkBiometric(){ //checa el biometrico
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(Authenticators.BIOMETRIC_STRONG or Authenticators.DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS ->{
                btnFinger.visibility= View.VISIBLE
                txtInfo.text=getString( R.string.biometric_succes)
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")

            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                txtInfo.text=getString( R.string.biometric_no_harware)
                Log.e("MY_APP_TAG", "No biometric features available on this device.")
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                txtInfo.text=getString( R.string.biometric_no_harware)+"_HW"
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> { //configuracion para mostrar la huella
                // Prompts the user to create credentials that your app accepts.
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        Authenticators.BIOMETRIC_STRONG or Authenticators.DEVICE_CREDENTIAL)
                }
                startActivityForResult(enrollIntent, 100)
                    //luego de configurar aparece en blanco porque no tiene un retorno (vista)
            }
        }
    }

}