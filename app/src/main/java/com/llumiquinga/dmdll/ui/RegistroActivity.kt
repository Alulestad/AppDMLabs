package com.llumiquinga.dmdll.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.databinding.ActivityLoginBinding
import com.llumiquinga.dmdll.databinding.ActivityRegistroBinding
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


            val intent= Intent(this,LoginActivity::class.java)
            Log.d(Constants.TAG,"Se creo el intent")
            startActivity(intent)
        }
    }
}