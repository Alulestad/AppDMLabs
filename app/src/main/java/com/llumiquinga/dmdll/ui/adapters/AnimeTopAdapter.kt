package com.llumiquinga.dmdll.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.llumiquinga.dmdll.R
import com.llumiquinga.dmdll.data.network.entities.jikan.top.Data
import com.llumiquinga.dmdll.databinding.DataAnimeItemsBinding

class AnimeTopAdapter(private val listDataAnime:List<Data>):RecyclerView.Adapter<AnimeTopAdapter.ViewHolderDataAnime>() {
    class ViewHolderDataAnime(view:View) :RecyclerView.ViewHolder(view){

        private var binding: DataAnimeItemsBinding=DataAnimeItemsBinding.bind(view)
        fun render(item: Data){
            binding.avatarImage.load(item.images.jpg.small_image_url)
            binding.txtID.text=item.title_english
            binding.txtName.text=item.synopsis

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDataAnime {
        val inflater= LayoutInflater.from(parent.context)
        return ViewHolderDataAnime(inflater.inflate(R.layout.data_anime_items,parent,false))

    }

    override fun getItemCount(): Int =  listDataAnime.size

    override fun onBindViewHolder(holder: ViewHolderDataAnime, position: Int) {
        holder.render(listDataAnime[position])

    }




}