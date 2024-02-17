package com.examenp.recliclerview.ui.activities


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
    //private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNobelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initObservers()
        initRecyclerView()

        viewModel.getAllNobelPrizes()

    }

    private fun initObservers() {

        viewModel.listItems.observe(this) { //Este es el observable
            binding.animationView.visibility = View.VISIBLE
            adapter.listNobels=it
            adapter.notifyDataSetChanged()
            binding.animationView.visibility = View.GONE
        }

        viewModel.error.observe(this) {
            adapter.listNobels = emptyList()
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