package com.examenp.recliclerview.logic.usercase.nobel

import android.util.Log


import com.examenp.recliclerview.core.Constants
import com.examenp.recliclerview.data.network.ApiOperation
import com.examenp.recliclerview.data.network.KtorClientNobel
import com.examenp.recliclerview.data.network.entities.nobel.Category
import com.examenp.recliclerview.data.network.entities.nobel.FullName
import com.examenp.recliclerview.data.network.entities.nobel.KnownName
import com.examenp.recliclerview.data.network.entities.nobel.Laureate
import com.examenp.recliclerview.data.network.entities.nobel.Motivation
import com.examenp.recliclerview.data.network.entities.nobel.NobelPrizeX
import com.examenp.recliclerview.data.network.entities.nobel.OrgName
import com.examenp.recliclerview.data.network.entities.nobelserializable.NobelPrizeXS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNobelPrizesUserCaseKtor {

    @Inject
    var ktorClientNobel: KtorClientNobel = KtorClientNobel()

    suspend fun invoke(limit: Int): Flow<Result<List<NobelPrizeX>>> = flow {
        var result: Result<List<NobelPrizeX>>? = null
        var newLimit=1


        val service = ktorClientNobel


        while (newLimit<=limit){
            val call = service.getAllNobelPrizes(newLimit)


            when (call) {
                is ApiOperation.Success -> {
                    result = Result.success(transformar(call.data.nobelPrizeXS))
                    // En caso de éxito, imprime el episodio
                    Log.d ("TEST_API", call.data.toString())
                }
                is ApiOperation.Failure -> {
                    val msg = "Error en el llamado a la API de Jikan"
                    result = Result.failure(Exception(msg))
                    // En caso de error, imprime el mensaje de error
                    Log.d ("TEST_API", call.exception.toString())
                }

                else -> {
                    Log.e(Constants.TAG, "Error")
                    result = Result.failure(Exception("Error"))
                }
            }

            emit(result!!)
            delay(2000)
            newLimit++
        }



    }

    private fun transformar(nobelPrizeXS: List<NobelPrizeXS>): List<NobelPrizeX> {
        var listaNobelFinal:MutableList<NobelPrizeX> = mutableListOf()
        nobelPrizeXS.forEach{
            listaNobelFinal.add(NobelPrizeX(
                it.awardYear,
                Category(it.category.en,"",""),
                it.categoryFullName,
                it.dateAwarded,
                transformarLaureates(it) ,
                listOf(),
                it.prizeAmount,
                it.prizeAmountAdjusted
                )
            )
        }

        return listaNobelFinal;
    }

    private fun transformarLaureates(it: NobelPrizeXS): List<Laureate> {
        var laureateFinal:MutableList<Laureate> = mutableListOf()

        it.laureates.forEach{
            laureateFinal.add(Laureate(
                FullName( it.fullName!!.en),
                it.id,
                KnownName(it.knownName.en),
                listOf(),
                Motivation(it.motivation.en!!,it.motivation.no!!,it.motivation.se!!),
                it.nativeName,
                OrgName(it.orgName.en,it.orgName.no),
                it.portion,
                it.sortOrder
            ))
        }

        return laureateFinal;
    }


}