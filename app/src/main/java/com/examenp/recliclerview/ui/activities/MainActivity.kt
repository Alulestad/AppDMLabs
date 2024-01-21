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
                "Daniel",
                "Estudiante",
                "https://www.google.com/search?sca_esv=b1246719c6e8d448&rlz=1C1VDKB_esEC1083EC1083&sxsrf=ACQVn088Y-nUmnSZ8k8RdJ05Fme0ZkZOPw:1705757571748&q=imagen+estudiante&tbm=isch&source=lnms&sa=X&ved=2ahUKEwjR-IuRiuyDAxWsRTABHXE2D5sQ0pQJegQIDBAB&biw=1707&bih=811&dpr=1.13#imgrc=cIwiQ2DmZXRWXM"
            )

            usersList.add(us)

            Log.d("List", usersList.toString())
            usersAdapter.listUsers=usersList
            usersAdapter.notifyDataSetChanged() //esto es lo que me permite vizualizar los insert
        }
    }
}