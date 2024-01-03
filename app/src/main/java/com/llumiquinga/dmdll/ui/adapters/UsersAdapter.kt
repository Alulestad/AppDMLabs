package com.llumiquinga.dmdll.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.data.local.entities.Users
import com.llumiquinga.dmdll.databinding.ActivityLoginBinding
import com.llumiquinga.dmdll.databinding.ActivityRegistroBinding
import com.llumiquinga.dmdll.databinding.UsersItemsBinding
import com.llumiquinga.dmdll.ui.core.Constants

class UsersAdapter(val listUsers:List<Users>):RecyclerView.Adapter<UsersAdapter.ViewHolderUsers>() {
    class ViewHolderUsers(view:View) :RecyclerView.ViewHolder(view){

        private var binding: UsersItemsBinding=UsersItemsBinding.bind(view)
        fun render(item: Users){
            //logica
            binding.imageView.load("https://img.freepik.com/psd-gratis/icono-3d-aplicacion-redes-sociales_23-2150049569.jpg?w=740&t=st=1703525227~exp=1703525827~hmac=6c64594dec38e681d6b1665f7acdba7b6258a6f6305881a01e34edbe8dff370b")

            Log.d(Constants.TAG,"UsersAdapter>render>item.userName: ${item.userName.toString()}")
            Log.d(Constants.TAG,"UsersAdapter>render>item.id.toString(): ${item.id.toString()}")
            Log.d(Constants.TAG,"UsersAdapter>render>item.firsName: ${item.firsName.toString()}")
            binding.txtID.text=item.id.toString()
            binding.txtName.text=item.userName

            binding.txtLastName.text=item.lastName

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUsers {
        val inflater= LayoutInflater.from(parent.context)
        return ViewHolderUsers(inflater.inflate(R.layout.users_items,parent,false))

    }

    override fun getItemCount(): Int =  listUsers.size

    override fun onBindViewHolder(holder: ViewHolderUsers, position: Int) {
        holder.render(listUsers[position])

    }




}