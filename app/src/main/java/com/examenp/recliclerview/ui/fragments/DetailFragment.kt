package com.examenp.recliclerview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.examenp.recliclerview.R
import com.examenp.recliclerview.databinding.FragmentDetailBinding
import com.examenp.recliclerview.logic.entities.FullInfoAnimeLG
import com.examenp.recliclerview.logic.usercase.jikan.JikanAnimeUserCase
import com.examenp.recliclerview.logic.usercase.jikan.JikanGetTopAnimesUserCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragment : Fragment() {
    private lateinit var binding:FragmentDetailBinding

    val args:DetailFragmentArgs by navArgs() //el by es un delegado

    private var usersList:FullInfoAnimeLG ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetailBinding.bind(inflater.inflate(R.layout.fragment_detail, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //var id=args.idAnime.toInt()

        //binding.txtIdAnime.text= id.toString()
        loadDataRecyclerView()
    }



    private fun loadDataRecyclerView(){
        var id=args.idAnime.toInt()

        lifecycleScope.launch(Dispatchers.Main) {

            val resp= withContext(Dispatchers.IO){
                JikanAnimeUserCase().getFullAnimeInfo(id)
            }
            resp.onSuccess {
                    anime ->
                run {
                    binding.txtIdAnime.text = id.toString()
                    binding.txtDuration.text=anime.duration.toString()
                    binding.txtTitleEnglish.text=anime.name.toString()
                    binding.txtType.text=anime.type.toString()
                    binding.txtEpisodes.text=anime.duration.toString()
                    binding.txtYear.text=anime.year.toString()
                    binding.txtUrl.text = anime.url.toString()
                }

            }
            resp.onFailure {ex->
                Snackbar.make(requireActivity(),binding.linear,ex.message.toString(), Snackbar.LENGTH_LONG).show()
            }


        }
    }





}