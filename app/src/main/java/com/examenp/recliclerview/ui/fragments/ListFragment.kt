package com.examenp.recliclerview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.examenp.recliclerview.R
import com.examenp.recliclerview.databinding.FragmentListBinding
import com.examenp.recliclerview.logic.entities.FullInfoAnimeLG
import com.examenp.recliclerview.logic.usercase.jikan.JikanGetTopAnimesUserCase
import com.examenp.recliclerview.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListFragment : Fragment() {
    private lateinit var binding:FragmentListBinding

    private var usersList:MutableList<FullInfoAnimeLG> = ArrayList()
    private var userDiffAdapter= UsersAdapterDiffUtil(
        {deleteUsersDiffUtil(it)}, {selectAnime(it)})
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentListBinding.bind( inflater.inflate(R.layout.fragment_list, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleViewDiffUtill()
    }
    fun initRecycleViewDiffUtill() {
        binding.rvUsers.adapter = userDiffAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        loadDataRecyclerView()


    }

    private fun loadDataRecyclerView(){
        lifecycleScope.launch(Dispatchers.Main) {
            binding.progresBar.visibility= View.VISIBLE

            val resp= withContext(Dispatchers.IO){
                JikanGetTopAnimesUserCase().invoke()
            }
            resp.onSuccess {
                    listAnime ->
                usersList.addAll(listAnime)
                insertUsersDiffUtil(usersList)
            }
            resp.onFailure {ex->
                Snackbar.make(requireActivity(),binding.rvUsers,ex.message.toString(), Snackbar.LENGTH_LONG).show()
            }


            binding.progresBar.visibility= View.GONE//porque ya se inicializo y ya puedo acceder a ella.
            //caso contrario nunca aparecera
        }
    }

    /*
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
*/
    fun initListenersDiffUtil() {

    }

    /*
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
*/
    private fun insertUsersDiffUtil(animes:List<FullInfoAnimeLG>) {
        usersList.addAll(animes)
        userDiffAdapter.submitList(usersList.toList())

    }



    private fun deleteUsersDiffUtil(position:Int){
        usersList.removeAt(position)
        userDiffAdapter.submitList(usersList.toList())
    }

    private fun selectAnime(anime: FullInfoAnimeLG){
       findNavController()
           .navigate(ListFragmentDirections
               .actionListFragmentToDetailFragment(idAnime = anime.id)
           )
    }


}