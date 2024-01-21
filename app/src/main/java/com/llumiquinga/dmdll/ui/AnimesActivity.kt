package com.llumiquinga.dmdll.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.core.My_Applicacion
import com.llumiquinga.dmdll.data.local.entities.Users
import com.llumiquinga.dmdll.data.network.entities.jikan.top.Data
import com.llumiquinga.dmdll.data.network.entities.jikan.top.TopAnime
import com.llumiquinga.dmdll.databinding.ActivityAnimesBinding
import com.llumiquinga.dmdll.logic.usercase.jikan.JikanGetTopAnimesUserCase
import com.llumiquinga.dmdll.logic.usercase.local.login.SingIn
import com.llumiquinga.dmdll.ui.adapters.AnimeTopAdapter
import com.llumiquinga.dmdll.ui.adapters.UsersAdapter
import com.llumiquinga.dmdll.ui.core.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAnimesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animes)

        binding=ActivityAnimesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initRecyclerViewTopAnime()
    }
/*
    private fun initRecyclerViewTopAnime() {
        lifecycleScope.launch (Dispatchers.Main){
            val dataA= withContext(Dispatchers.IO){getAllTopAnimes()}
            Log.d(Constants.TAG,"MainActivity>initRecyclerViewTopAnime> "+dataA)
            Log.d(Constants.TAG,"MainActivity>initRecyclerViewTopAnime> 1 synopsis>"+dataA.first().synopsis)
            val adapter: AnimeTopAdapter = AnimeTopAdapter(dataA)
            binding.rvData.adapter=adapter
            binding.rvData.layoutManager=
                LinearLayoutManager(this@AnimesActivity,
                    LinearLayoutManager.VERTICAL,false)
            binding.pbPrincipal.visibility= View.GONE
        }
    }}
    */

    suspend fun getAnimeList() :List<Users>{
        delay(1000)
        var allusers= SingIn(My_Applicacion.getConnectionDB()!!).getAllUsers()
        Log.d(Constants.TAG,"MainActivity>getUsersList> "+allusers)
        Log.d(Constants.TAG,"MainActivity>getUsersList> 1 Nombre>"+allusers.first().firsName)

        return SingIn(My_Applicacion.getConnectionDB()!!)
            .getAllUsers()
    }

    /*
    private suspend fun getAllTopAnimes():List<Data> {
            val x= JikanGetTopAnimesUserCase().getResponse().data
        return x
    }
*/

}