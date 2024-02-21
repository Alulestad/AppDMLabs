package com.examenp.recliclerview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.examenp.recliclerview.R
import com.examenp.recliclerview.data.local.entities.Users
import com.examenp.recliclerview.databinding.ItemsUsersBinding

class UsersAdapter(
    private val onDeleteItem:(Int) -> Unit,
    private val onSelectItem:(Users)->Unit
):RecyclerView.Adapter<UsersAdapter.ViewHolderUsers>() {
    var listUsers:List<Users> = listOf()

    class ViewHolderUsers(view: View) :RecyclerView.ViewHolder(view){

        private var binding: ItemsUsersBinding=ItemsUsersBinding.bind(view)
        fun render(
            item: Users
            ,onDeleteItem:(Int) -> Unit
            , onSelectItem:(Users)->Unit  ){
            binding.txtUserName.text=item.name
            binding.txtUserDesc.text=item.desc
            binding.imgUser.load(item.img)

            binding.btnBorrar.setOnClickListener {
                onDeleteItem(adapterPosition)
            }

            binding.imgUser.setOnClickListener {
                onSelectItem(item)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUsers {
        val inflater= LayoutInflater.from(parent.context)
        return ViewHolderUsers(inflater.inflate(R.layout.items_users,parent,false))

    }

    override fun getItemCount(): Int =  listUsers.size

    override fun onBindViewHolder(holder: ViewHolderUsers, position: Int) {

        holder.render(listUsers[position],onDeleteItem , onSelectItem)

    }


}