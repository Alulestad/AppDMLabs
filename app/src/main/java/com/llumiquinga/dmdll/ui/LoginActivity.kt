package com.llumiquinga.dmdll.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.databinding.ActivityLoginBinding
import com.llumiquinga.dmdll.logic.login.SingIn
import com.llumiquinga.dmdll.ui.core.Constants

class LoginActivity : AppCompatActivity() {

    private var usuarios =mutableListOf<String>()
    private var contrasenia =mutableListOf<String>()
    private val singIn:SingIn=SingIn()


    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Usario y contraseña base
        usuarios.add("admin1")
        contrasenia.add("admin1")
        usuarios.add("admin2")
        contrasenia.add("admin2")
        usuarios.add("admin3")
        contrasenia.add("admin3")



    }

    override fun onStart() {
        super.onStart()
        initControls()


    }

    override fun onDestroy() {
        super.onDestroy()
    }


    fun initControls(){

        binding.lyRoot.setOnClickListener {

        }


        binding.btnLogin.setOnClickListener {
            var u= binding.txtUsuario.text.toString()
            var p= binding.txtPass.text.toString()

            if (!singIn.checkUserAndPassword(u,p)){
                //Toast.makeText(this, u, Toast.LENGTH_LONG).show()
                //Snackbar.make(binding.btnLogin,u,Snackbar.LENGTH_LONG).show()
                Snackbar.make(binding.btnLogin,"usuario o" +
                        "password incorrecto",Snackbar.LENGTH_LONG).show()
            }else{
                // aca se activa la llamada al activity main
                val intent= Intent(this,MainActivity::class.java)
                intent.putExtra(Constants.TEXT_VARIABLE,"mi primera")
                startActivity(intent)
            }




        }

        binding.btngoogle.setOnClickListener {
            val intentImpl=Intent()
            intentImpl.action=Intent.ACTION_SEND
            intentImpl.putExtra(Intent.EXTRA_TEXT
                ,"Mi primera chamba")
            intentImpl.type="text/plain"
            startActivity(intentImpl)
        }
    }

    fun validacionUsuario():Boolean{

        var user_ingresado=binding.txtUsuario.text.toString()
        var pass_ingresado=binding.txtPass.text.toString()
        for(indice in usuarios.indices){
            print("Se ingresa al sistema")

        }
    return true
    }


}