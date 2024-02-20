package com.examenp.recliclerview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.examenp.recliclerview.R

import com.examenp.recliclerview.data.network.entities.nobel.NobelPrizeX
import com.examenp.recliclerview.databinding.NobelItemsBinding


class NobelPrizeAdapter : ListAdapter<NobelPrizeX,NobelPrizeAdapter.AnimeVH>(DiffUtilNobelCallback) {


    class AnimeVH(view: View) : RecyclerView.ViewHolder(view) {

        private var binding: NobelItemsBinding = NobelItemsBinding.bind(view)

        fun render(item: NobelPrizeX) {
            binding.imgNobel.load("https://openclipart.org/image/800px/167281")
            binding.txtAnio.text = item.awardYear
            binding.txtCategoria.text = item.category!!.en
            binding.txtNombre.text = item.laureates?.get(0)?.fullName?.en
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeVH {
        val inflater = LayoutInflater.from(parent.context)
        return AnimeVH(inflater.inflate(R.layout.nobel_items, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeVH, position: Int) {
        holder.render(getItem(position))
    }
}

    object DiffUtilNobelCallback : DiffUtil.ItemCallback<NobelPrizeX>() {

        override fun areItemsTheSame(oldItem: NobelPrizeX, newItem: NobelPrizeX): Boolean {
            return (oldItem.dateAwarded == newItem.dateAwarded)
        }

        override fun areContentsTheSame(oldItem: NobelPrizeX, newItem: NobelPrizeX): Boolean {
            return (oldItem == newItem)
        }
}
