package com.examenp.recliclerview.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.examenp.recliclerview.R
import com.examenp.recliclerview.data.entities.Users
import com.examenp.recliclerview.databinding.ActivityMainBinding
import com.examenp.recliclerview.ui.adapters.UsersAdapter

class MainActivity : AppCompatActivity() {

    private var usersList:MutableList<Users> = ArrayList<Users>()
    private var count : Int = 1

    private lateinit var binding:ActivityMainBinding
    private var usersAdapter = UsersAdapter()// =UsersAdapter(usersList.toList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecycleView()
        initListeners()


    }

    fun initRecycleView() {
        binding.rvUsers.adapter = usersAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

    }

    fun initListeners() {
        binding.btnInsert.setOnClickListener {
            val us = Users(
                1,
                "Daniel $count",
                "Estudiante",
                "https://m.media-amazon.com/images/I/51iw5yGdfGL.jpg"
            )
            count++
            usersList.add(us)

            Log.d("List", usersList.toString())
            usersAdapter.listUsers=usersList
            usersAdapter.notifyDataSetChanged() //esto informa al adaptador que existe un cambio.
                                                //y este se informa a travez de la lista mutable
                                                //la desventaja es que envia todo y no discrimina
                                                //si exsite un elemento nuevo o no.

        }
    }
}