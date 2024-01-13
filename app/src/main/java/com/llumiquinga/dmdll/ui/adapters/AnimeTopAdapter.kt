package com.llumiquinga.dmdll.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.data.network.entities.jikan.top.Data
import com.llumiquinga.dmdll.databinding.DataItemsBinding
import com.llumiquinga.dmdll.ui.core.Constants

class AnimeTopAdapter(val listDataAnime:List<Data>):RecyclerView.Adapter<AnimeTopAdapter.ViewHolderData>() {
    class ViewHolderData(view:View) :RecyclerView.ViewHolder(view){

        private var binding: DataItemsBinding=DataItemsBinding.bind(view)
        fun render(item: Data){
            //logica
            binding.imageView.load("https://img.freepik.com/psd-gratis/icono-3d-aplicacion-redes-sociales_23-2150049569.jpg?w=740&t=st=1703525227~exp=1703525827~hmac=6c64594dec38e681d6b1665f7acdba7b6258a6f6305881a01e34edbe8dff370b")

            Log.d(Constants.TAG,"UsersAdapter>render>item.synopsis: ${item.title.toString()}")
            binding.txtTitle.text=item.title
            binding.txtYear.text=item.year.toString()

            binding.txtStatus.text=item.synopsis

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val inflater= LayoutInflater.from(parent.context)
        return ViewHolderData(inflater.inflate(R.layout.data_items,parent,false))

    }

    override fun getItemCount(): Int =  listDataAnime.size

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        holder.render(listDataAnime[position])

    }




}