package com.llumiquinga.dmdll.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.SuperscriptSpan
import androidx.lifecycle.lifecycleScope
import com.creative.ipfyandroid.Ipfy
import com.creative.ipfyandroid.IpfyClass
import com.google.android.material.snackbar.Snackbar
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.core.My_Applicacion
import com.llumiquinga.dmdll.databinding.ActivityMainBinding
import com.llumiquinga.dmdll.logic.login.SingIn
import com.llumiquinga.dmdll.ui.core.Constants
import com.llumiquinga.dmdll.ui.fragments.FragmentFavorites
import com.llumiquinga.dmdll.ui.fragments.List1Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // uso del ipify
        Ipfy.init(this) // this is a context of application
        //or you can also pass IpfyClass type to get either IPv4 address only or universal address IPv4/v6 as
        Ipfy.init(this,IpfyClass.IPv4) //to get only IPv4 address
        //and
        Ipfy.init(this,IpfyClass.UniversalIP) //to get Universal address in IPv4/v6

        getIpAddress()

        intent.extras.let{

            lifecycleScope.launch (Dispatchers.IO){
                intentConecction()
            }


            val userId=it?.getInt(Constants.USER_ID)
            if(userId!=null){
                val user=SingIn(My_Applicacion.getConnectionDB()!!)
                    .getUserName3(userId) //ojo estaba
                binding.textView.text=user.id.toString()
            }else{
                // se deberia mandar a un activity de error.
                Snackbar.make(binding.textView,"error",Snackbar.LENGTH_LONG).show()
            }

        }


        //////////FRAGMENTS
        val list1Fragment=List1Fragment()
        val fragmentFavorites=FragmentFavorites()




        //transaccion.replace(binding.frmContainer.id,list1Fragment)
        //transaccion.replace(binding.frmContainer2.id,fragmentFavorites)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.it_home -> {
                    val transaccion=supportFragmentManager.beginTransaction()

                    transaccion.replace(binding.frmContainer.id,list1Fragment)
                    transaccion.commit()
                    // Respond to navigation item 1 click
                    true
                }
                R.id.it_fav -> {
                    val transaccion=supportFragmentManager.beginTransaction()

                    transaccion.replace(binding.frmContainer.id,fragmentFavorites)
                    transaccion.commit()
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }

        }

    }

    suspend fun intentConecction(){
        My_Applicacion.getConnectionDB()!!.getUserDAO().getUser(3)
    }

    private fun getIpAddress(){
        Ipfy.getInstance().getPublicIpObserver().observe(this, { ipData ->
            binding.txtIp.text=ipData.currentIpAddress // this is a value which is your current public IP address, null if no/lost internet connection


        })
    }

    override fun onStart() {
        super.onStart()
        initControls()


    }

    fun initControls(){

        binding.btnlogout.setOnClickListener {
                // aca se activa la llamada al activity main
                val intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)





        }
    }




}