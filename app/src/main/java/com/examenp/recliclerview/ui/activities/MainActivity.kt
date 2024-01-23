package com.examenp.recliclerview.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.examenp.recliclerview.R
import com.examenp.recliclerview.data.entities.Users
import com.examenp.recliclerview.databinding.ActivityMainBinding
import com.examenp.recliclerview.ui.adapters.UsersAdapter
import com.examenp.recliclerview.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var usersList:MutableList<Users> = ArrayList<Users>()
    private var count : Int = 1

    private lateinit var binding:ActivityMainBinding
    private var usersAdapter = UsersAdapter(
        {deleteUsers(it)}, {selectUser(it)})// =UsersAdapter(usersList.toList())
    private var userDiffAdapter=UsersAdapterDiffUtil(
        {deleteUsersDiffUtil(it)}, {selectUser(it)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecycleViewDiffUtill()
        initListenersDiffUtil()


    }

    fun initRecycleView() {
        binding.rvUsers.adapter = usersAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

    }

    fun initRecycleViewDiffUtill() {
        binding.rvUsers.adapter = userDiffAdapter
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


            Log.d("List", usersList.toString())
            insertUsers(us)

        }
    }

    fun initListenersDiffUtil() {
        binding.btnInsert.setOnClickListener {
            val us = Users(
                1,
                "Daniel $count",
                "Estudiante",
                "https://m.media-amazon.com/images/I/51iw5yGdfGL.jpg"
            )
            count++


            Log.d("List", usersList.toString())
            insertUsersDiffUtil(us)

        }
    }

    private fun insertUsers(us:Users) {
        usersList.add(us)
        usersAdapter.listUsers=usersList
        usersAdapter.notifyItemInserted(usersList.size)
                                            //estoy haciendo una notificacion
                                            //de lo extra que se guarda
    }

    private fun deleteUsers(position:Int){
        usersList.removeAt(position)
        usersAdapter.listUsers=usersList
        usersAdapter.notifyItemRemoved(position)
    }

    private fun insertUsersDiffUtil(us:Users) {
        usersList.add(us)
        userDiffAdapter.submitList(usersList.toList())

    }

    private fun deleteUsersDiffUtil(position:Int){
        usersList.removeAt(position)
        userDiffAdapter.submitList(usersList.toList())
    }

    private fun selectUser(user:Users){
        Snackbar
            .make(this.binding.btnInsert,user.name,Snackbar.LENGTH_LONG)
            .show()

        /*
        val i=Intent(this,llegada)
        i.putExtra("usuarioID", user.id)
        startActivity(i)

         */
    }
}