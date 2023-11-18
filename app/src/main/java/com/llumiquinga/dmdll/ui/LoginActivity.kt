package com.llumiquinga.dmdll.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var usuarios =mutableListOf<String>()
    private var contrasenia =mutableListOf<String>()


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

            //Toast.makeText(this, u, Toast.LENGTH_LONG).show()
            Snackbar.make(binding.btnLogin,u,Snackbar.LENGTH_LONG).show()
        }
    }

    fun validacionUsuario():Boolean{

        var user_ingresado=binding.txtUsuario.text.toString()
        var pass_ingresado=binding.txtPass.text.toString()
        for(indice in usuarios.indices){

        }
    return false
    }

}