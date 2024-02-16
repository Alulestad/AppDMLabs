package com.examenp.recliclerview.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examenp.recliclerview.data.network.entities.nobel.NobelPrizeX
import com.examenp.recliclerview.logic.entities.FullInfoAnimeLG
import com.examenp.recliclerview.logic.usercase.jikan.JikanAnimeUserCase
import com.examenp.recliclerview.logic.usercase.nobel.GetAllNobelPrizesUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NobelViewModel:ViewModel(){

    val listItems = MutableLiveData<List<NobelPrizeX>>()
    val error = MutableLiveData<String>()

    fun getAllNobelPrizes() {
        viewModelScope.launch(Dispatchers.IO) {

            val userCase = GetAllNobelPrizesUserCase()
            val nobelFlow = userCase.invoke(1)

            nobelFlow.collect{ nobel ->
                    nobel.onSuccess {
                        listItems.postValue(it.toList())
                    }
                    nobel.onFailure {
                        error.postValue(it.message.toString())
                    }
                }
        }
    }
}