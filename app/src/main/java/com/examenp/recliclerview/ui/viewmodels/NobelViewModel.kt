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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NobelViewModel : ViewModel() {

    val listItems = MutableLiveData<List<NobelPrizeX>>()
    val error = MutableLiveData<String>()

    fun getAllNobelPrizes() { //Aca yo hago uncamenta la consulta en el IO
        viewModelScope.launch(Dispatchers.IO) {

            val userCase = GetAllNobelPrizesUserCase()
            val nobelFlow = userCase.invoke(1)

            nobelFlow
                .collect{nobel -> //recolecta lo de la tuberia
                nobel.onSuccess {
                    listItems.postValue(it.toList())
                }
                nobel.onFailure {
                    error.postValue(it.message.toString())
                }
            }
                // .runCatching {}en caso de generar errores
                // .takeIf {  } tomar en caso de una condicion por ejemplo que sea menores al año 2010



        }

    }
}