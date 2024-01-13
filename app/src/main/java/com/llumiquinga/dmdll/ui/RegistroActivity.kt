package com.llumiquinga.dmdll.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.core.My_Applicacion
import com.llumiquinga.dmdll.databinding.ActivityLoginBinding
import com.llumiquinga.dmdll.databinding.ActivityRegistroBinding
import com.llumiquinga.dmdll.logic.usercase.local.login.Registro
import com.llumiquinga.dmdll.logic.usercase.local.login.SingIn
import com.llumiquinga.dmdll.ui.core.Constants

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        binding=ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        initControls()
    }
    fun initControls(){
        binding.btnSalir.setOnClickListener{
            val intent= Intent(this,LoginActivity::class.java)
            Log.d(Constants.TAG,"Se creo el intent")
            startActivity(intent)
        }
        binding.btnRegistrarse.setOnClickListener {
            Log.d(Constants.TAG,"RegistroActivity>initControls>binding.btnRegistrarse: Se ejecuto el binding.btnRegistrarse")
            val check:Int= Registro(My_Applicacion.getConnectionDB()!!).registrar(
                binding.edtxtUsuario.text.toString(),
                binding.edtxtApellido.text.toString(),
                binding.edtxtEmail.text.toString(),
                binding.edtxtNombre.text.toString(),
                binding.edtxtPassw.text.toString(),
                binding.edtxtPassw2.text.toString()
            )


            val intent= Intent(this,LoginActivity::class.java)
            Log.d(Constants.TAG,"RegistroActivity>initControls>binding.btnRegistrarse>check: $check")
            intent.putExtra(Constants.USER_ID,check)
            startActivity(intent)
        }
    }
}