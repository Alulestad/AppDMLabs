package com.examenp.recliclerview.ui.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.examenp.recliclerview.R
import com.examenp.recliclerview.databinding.ActivityLoginBinding
import com.examenp.recliclerview.ui.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth //OJOOOOOOOOOOO ktx
import com.google.firebase.ktx.Firebase
import java.util.concurrent.Executor

class LoginActivity : AppCompatActivity() {

    //firebase
    private lateinit var auth: FirebaseAuth

    private lateinit var binding:ActivityLoginBinding

    //private lateinit var btnFinger:ImageView
    //private lateinit var txtInfo:TextView

    private lateinit var executor: Executor //permite ejecutar app en segundo plano
        //necesita hilos
    private lateinit var biometricPrompt: BiometricPrompt   // maja los eventos del biometrico
    private lateinit var promptInfo: BiometricPrompt.PromptInfo //es el dialogo, lo que se meustra


    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_login)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth


        initListener()
        initObservables()
        AutenticationVariables()
        loginViewModel.checkBiometric(this)

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            binding.etxtUser.visibility= View.GONE
            binding.etxtPassword.visibility= View.GONE

            binding.imgFinger.visibility=View.VISIBLE
            binding.txtInfo.text=getString(R.string.biometric_succes)

            startActivity(Intent(this,MainActivity::class.java))
        }else{

            binding.imgFinger.visibility= View.GONE
            binding.txtInfo.text=getString(R.string.no_user)
        }

    }

    private fun initListener(){

        binding.imgFinger
        binding.imgFinger.setOnClickListener{
            biometricPrompt.authenticate(promptInfo)

        }

        binding.btnSaveUser.setOnClickListener {
            createNewUsers(binding.etxtUser.text.toString(), binding.etxtPassword.text.toString())
        }
        binding.btnSignUser.setOnClickListener {

            SignInUsers(binding.etxtUser.text.toString(),binding.etxtPassword.text.toString())

        }
    }

    private fun AutenticationVariables(){
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(Intent(this@LoginActivity,MainActivity2::class.java))
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setAllowedAuthenticators(Authenticators.BIOMETRIC_STRONG or Authenticators.DEVICE_CREDENTIAL)
            .build()

    }

    private fun initObservables(){

        loginViewModel.resultCheckBiometric.observe(this){
            //it=code
            when(it){
                BiometricManager.BIOMETRIC_SUCCESS->{
                    //binding.imgFinger.visibility= View.VISIBLE
                    binding.txtInfo.text=getString( R.string.biometric_succes)
                    Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
                }
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE->{
                    binding.txtInfo.text=getString( R.string.biometric_no_harware)
                    Log.e("MY_APP_TAG", "No biometric features available on this device.")
                }
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE->{
                    binding.txtInfo.text=getString( R.string.biometric_no_harware)+"_HW"
                    Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
                }
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED->{
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(
                            Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                            BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                    }
                    startActivityForResult(enrollIntent, 100)
                    //luego de configurar aparece en blanco porque no tiene un retorno (vista)
                }
            }
        }

    }

    private fun createNewUsers(user:String, password:String){
        auth.createUserWithEmailAndPassword(user,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    Snackbar.make(this,binding.etxtUser,"CreateUserWithEmail: Success",Snackbar.LENGTH_LONG).show()
                    binding.etxtUser.text.clear()
                    binding.etxtPassword.text.clear()

                } else {
                    // If sign in fails, display a message to the user.
                    Snackbar.make(this,binding.etxtUser,task.exception!!.message.toString(),Snackbar.LENGTH_LONG).show()

                }
            }
    }
    private fun SignInUsers(email:String,password:String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    startActivity(Intent(this@LoginActivity,MainActivity2::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Snackbar.make(this,binding.etxtUser,"signInWithEmail:failure",Snackbar.LENGTH_LONG).show()

                }
            }
    }

}