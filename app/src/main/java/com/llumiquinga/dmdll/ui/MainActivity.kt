package com.llumiquinga.dmdll.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.SuperscriptSpan
import android.util.Log
import android.view.View
import androidx.activity.contextaware.withContextAvailable
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.creative.ipfyandroid.Ipfy
import com.creative.ipfyandroid.IpfyClass
import com.google.android.material.snackbar.Snackbar
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.core.My_Applicacion
import com.llumiquinga.dmdll.data.local.entities.Users
import com.llumiquinga.dmdll.databinding.ActivityMainBinding
import com.llumiquinga.dmdll.logic.usercase.local.login.SingIn
import com.llumiquinga.dmdll.logic.usercase.jikan.JikanAnimeUserCase
import com.llumiquinga.dmdll.logic.usercase.jikan.JikanGetTopAnimesUserCase
import com.llumiquinga.dmdll.ui.adapters.AnimeTopAdapter
import com.llumiquinga.dmdll.ui.adapters.UsersAdapter
import com.llumiquinga.dmdll.ui.core.Constants
import com.llumiquinga.dmdll.ui.fragments.FragmentFavorites
import com.llumiquinga.dmdll.ui.fragments.List1Fragment
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        checkDataBase()
        initRecyclerView()
        initRecyclerView1()

        val a=JikanAnimeUserCase()
        //aca ya llama automaticamente como si fuera con un invoke
        getAllTopAnimes()

    }

    /*private fun getAllTopAnimes() {
        lifecycleScope.launch(Dispatchers.IO) {
            val x=JikanGetTopAnimesUserCase().getResponse()
            Log.d(Constants.TAG,"x.pagination.toString()")
            //Log.d(Constants.TAG,x.pagination.toString())
            //Log.d(Constants.TAG,x.data[0].synopsis)
        }

    }*/

    private fun initRecyclerView() {
        lifecycleScope.launch (Dispatchers.Main){
            val usrs= withContext(Dispatchers.IO){getUsersList()}
            Log.d(Constants.TAG,"MainActivity>initRecyclerView> "+usrs)
            Log.d(Constants.TAG,"MainActivity>initRecyclerView> 1 Nombre>"+usrs.first().firsName)
            val adapter:UsersAdapter= UsersAdapter(usrs)
            binding.rvUsers.adapter=adapter
            binding.rvUsers.layoutManager=
                LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL,false)
            binding.pbPrincipal.visibility= View.GONE
        }
    }


    private fun getAllTopAnimes(){
        //binding.animationView.visibility=View.VISIBLE.
    }
    private fun initRecyclerView1() {

        lifecycleScope.launch (Dispatchers.Main){
            //binding.animationView.visibility=View.VISIBLE.
            var jikan =JikanGetTopAnimesUserCase()
            val animes= withContext(Dispatchers.IO){jikan.getResponse()}
            animes.onSuccess{animes->
                Log.d(Constants.TAG,"MainActivity>initRecyclerView1>animes "+animes.toString())
                val adapter= AnimeTopAdapter(animes.data)
                binding.rvUsers.adapter=adapter
                binding.rvUsers.layoutManager=
                    LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.VERTICAL,
                        false)


            }

            animes.onFailure{
                //manejo de errores en la UI

            }

            binding.pbPrincipal.visibility= View.GONE
        }
    }




    private fun checkDataBase() {
        lifecycleScope.launch(Dispatchers.Main) {
            val usrs= withContext(Dispatchers.IO){
                SingIn(My_Applicacion.getConnectionDB()!!)
                    .getAllUsers()
            }
            Log.d(Constants.TAG,"MainActivity>checkDataBase> "+usrs.toString())
            Log.d(Constants.TAG,"MainActivity>checkDataBase> 1 Nombre>"+usrs.first().firsName)

        }


    }

    suspend fun getUsersList() :List<Users>{
        delay(1000)
        var allusers= SingIn(My_Applicacion.getConnectionDB()!!).getAllUsers()
        Log.d(Constants.TAG,"MainActivity>getUsersList> "+allusers)
        Log.d(Constants.TAG,"MainActivity>getUsersList> 1 Nombre>"+allusers.first().firsName)

        return SingIn(My_Applicacion.getConnectionDB()!!)
                    .getAllUsers()
    }

    private fun initListeners() {
        //Inicio recargar
        binding.swiperv.setOnRefreshListener {
            val adapter= AnimeTopAdapter(listOf())
            binding.rvUsers.adapter=adapter
            binding.rvUsers.layoutManager=
                LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL,
                    false)
            initRecyclerView1()
            binding.swiperv.isRefreshing=false

        }

        //Fin recargar


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
                val user= SingIn(My_Applicacion.getConnectionDB()!!)
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
            val manager=supportFragmentManager
            when(item.itemId) {
                R.id.it_home -> {
                    val transaccion=manager.beginTransaction()

                    transaccion.replace(binding.frmContainer.id,List1Fragment())
                    transaccion.commit()
                    // Respond to navigation item 1 click
                    true
                }
                R.id.it_fav -> {
                    val transaccion=manager.beginTransaction()

                    transaccion.replace(binding.frmContainer.id,fragmentFavorites)
                    transaccion.commit()
                    // Respond to navigation item 2 click
                    true
                }
                else -> {

                    lifeScopeCorrutinas();
                    false
                }
            }

        }

    }

    private fun lifeScopeCorrutinas() {
        lifecycleScope.launch(Dispatchers.IO) {
            val a = "daniel"
            withContext(Dispatchers.Main) {
                binding.textView4.text = a
            }

        }

        lifecycleScope.launch(Dispatchers.Main) {
            val name = withContext(Dispatchers.IO){
                val a="Bayron"
                val b=a+"Torres"

                b
            }
            /*
            val s =async {
                val a=""
                a
            }
            val s2 =async {
                val a=""
                a
            }



            val deferreds: List<Deferred<String>> = mutableListOf(s,s2)

            deferreds.awaitAll()
*/
            val w= withContext(Dispatchers.Default){
                val listC=listOf(
                    async { getName() },
                    async { getName() }
                )
                val w1= listC.awaitAll()
            }


            val name1 = withContext(Dispatchers.IO){
                getName()
            }

            binding.textView4.text=name1.toString()
        }
    }


    suspend fun getName():String{
        val a="Carlos"
        val b= a+"Mapache"
        return b
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