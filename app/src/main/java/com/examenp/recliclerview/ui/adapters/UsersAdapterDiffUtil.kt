package com.examenp.recliclerview.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.examenp.recliclerview.R
import com.examenp.recliclerview.data.entities.Users
import com.examenp.recliclerview.databinding.ItemsUsersBinding
import com.examenp.recliclerview.logic.entities.FullInfoAnimeLG

class UsersAdapterDiffUtil(
    private val onDeleteItem:(Int) -> Unit,
    private val onSelectItem:(FullInfoAnimeLG)->Unit
):ListAdapter<FullInfoAnimeLG,UsersAdapterDiffUtil.ViewHolderUsers>(DiffUtilUserCallback) {


    class ViewHolderUsers(view: View) :RecyclerView.ViewHolder(view){

        private var binding: ItemsUsersBinding=ItemsUsersBinding.bind(view)
        fun render(
            item: FullInfoAnimeLG
            ,onDeleteItem:(Int) -> Unit
            , onSelectItem:(FullInfoAnimeLG)->Unit  ){
            binding.txtUserName.text=item.name
            binding.txtUserDesc.text=item.synopsis
            binding.imgUser.load(item.big_image)

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


    override fun onBindViewHolder(holder: ViewHolderUsers, position: Int) {

        holder.render(getItem(position),onDeleteItem , onSelectItem)

    }


}

private object DiffUtilUserCallback : DiffUtil.ItemCallback<FullInfoAnimeLG>() {
    override fun areItemsTheSame(oldItem: FullInfoAnimeLG, newItem: FullInfoAnimeLG): Boolean {
        return (oldItem.id==newItem.id)
    }

    override fun areContentsTheSame(oldItem: FullInfoAnimeLG, newItem: FullInfoAnimeLG): Boolean {
        return (oldItem==newItem)
    }


}
