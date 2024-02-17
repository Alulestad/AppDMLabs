package com.examenp.recliclerview.ui.activities


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.examenp.recliclerview.R
import com.examenp.recliclerview.databinding.ActivityNobelBinding
import com.examenp.recliclerview.ui.adapters.NobelPrizeAdapter
import com.examenp.recliclerview.ui.viewmodels.NobelViewModel

class NobelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNobelBinding
    private val adapter =NobelPrizeAdapter()
    private val viewModel:NobelViewModel by viewModels()
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNobelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initObservers()
        initRecyclerView()

        dialog=AlertDialog.Builder(this)
            .setMessage("Desea iniciar con la carga de los datos")
            .setTitle("Mensaje de informacion")
            .setPositiveButton("Aceptar"){_,_->
                viewModel.getAllNobelPrizes()
            }
            .setNegativeButton("Cancelar"){dialog,_->
                dialog.dismiss()
            }
            .setCancelable(true) //si tocan afuera se cancela, false: si quero que tomen alguna de las opciones
            .create()


        dialog.show()



    }

    private fun initObservers() {

        viewModel.listItems.observe(this) { //Este es el observable
            binding.animationView.visibility = View.VISIBLE
            adapter.submitList(it)
            binding.animationView.visibility = View.GONE
        }

        viewModel.error.observe(this) {
            adapter.submitList(emptyList())
            adapter.notifyDataSetChanged()
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
    }


}