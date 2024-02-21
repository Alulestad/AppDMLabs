package com.examenp.recliclerview.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examenp.recliclerview.data.network.entities.nobel.NobelPrizeX
import com.examenp.recliclerview.logic.network.usercase.nobel.GetAllNobelPrizesKtor
import com.examenp.recliclerview.logic.network.usercase.nobel.GetAllNobelPrizesUserCaseKtor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NobelViewModel : ViewModel() {

    val listItems = MutableLiveData<List<NobelPrizeX>>()
    val error = MutableLiveData<String>()

    fun getAllNobelPrizes() { //Aca yo hago uncamenta la consulta en el IO
        viewModelScope.launch(Dispatchers.IO) {
            val userCase = GetAllNobelPrizesKtor()
            val nobelFlow = userCase.invoke(5)

            nobelFlow
                .collect { nobel -> //recolecta lo de la tuberia
                    nobel.onSuccess {
                        listItems.postValue(it.toList())
                    }
                    nobel.onFailure {
                        error.postValue(it.message.toString())
                    }

                }

        }
    }

    fun getAllNobelPrizesTrabajoEnGrupo() { //Aca yo hago uncamenta la consulta en el IO
        viewModelScope.launch(Dispatchers.IO) {

            val userCase = GetAllNobelPrizesUserCaseKtor()
            val nobelFlow = userCase.invoke(5)

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