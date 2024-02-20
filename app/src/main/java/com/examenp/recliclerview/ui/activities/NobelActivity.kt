package com.examenp.recliclerview.ui.activities


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examenp.recliclerview.R
import com.examenp.recliclerview.data.network.ApiOperation
import com.examenp.recliclerview.databinding.ActivityNobelBinding
import com.examenp.recliclerview.ui.adapters.NobelPrizeAdapter
import com.examenp.recliclerview.ui.viewmodels.NobelViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NobelActivity : AppCompatActivity() {

    //Daniel Llumiquigna
    //Jhonatan Altamirano
    //Jimmy Ortega

    private lateinit var binding: ActivityNobelBinding
    private val adapter =NobelPrizeAdapter()
    private val viewModel:NobelViewModel by viewModels()
    private lateinit var dialog: AlertDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNobelBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initVariables("")
        initRecyclerView()
        initObservers()
        initListeners()
        swipeRecyclerView()

        viewModel.getAllNobelPrizes()

    }

    private fun initVariables(messageError:String) {
        dialog=AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_dialog))
            .setMessage(messageError)
            .setPositiveButton(getString(R.string.aceptar)){ dialog, _->
                //viewModel.getAllNobelPrizes()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancelar)){ dialog, _->
                dialog.dismiss()
            }
            .setCancelable(true) //si tocan afuera se cancela, false: si quero que tomen alguna de las opciones
            .create()



    }

    private fun initObservers() {

        viewModel.listItems.observe(this) { //Este es el observable
            binding.animationView.visibility = View.VISIBLE
            adapter.submitList(it)
            binding.animationView.visibility = View.GONE
        }

        viewModel.error.observe(this) {
            adapter.submitList(emptyList())
            initVariables(it)
            dialog.show()
        }
    }


    private fun initRecyclerView() {
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager =
            LinearLayoutManager(
                this@NobelActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    private fun initListeners() {
        binding.swiperv.setOnRefreshListener {
            viewModel.getAllNobelPrizes()
            binding.swiperv.isRefreshing = false
        }

        binding.edtFiltro.addTextChangedListener {filtro->
            //Log.d("TAG",filtro.toString())
            val listFilter= adapter.currentList.toList().filter {item->
                item.category?.en?.contains(filtro.toString())!!
            }
            adapter.submitList(listFilter)

            if(filtro.isNullOrBlank()){
                viewModel.getAllNobelPrizes()
            }
        }
    }

    private fun swipeRecyclerView() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                //Log.d("TAG","Functio swipe")
                val position= viewHolder.adapterPosition
                val list= adapter.currentList.toMutableList()
                list.removeAt(position)
                adapter.submitList(list)
            }
        }).attachToRecyclerView(binding.rvUsers)
    }


}