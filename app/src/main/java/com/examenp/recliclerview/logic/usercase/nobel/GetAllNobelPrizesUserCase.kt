package com.examenp.recliclerview.logic.usercase.nobel

import android.util.Log


import com.examenp.recliclerview.core.Constants
import com.examenp.recliclerview.core.getFullInfoAnimeLG
import com.examenp.recliclerview.data.network.endpoints.anime.TopAnimesEndPoint
import com.examenp.recliclerview.data.network.endpoints.nobel.NobelPrizeEndPoint
import com.examenp.recliclerview.data.network.entities.nobel.NobelPrizeX
import com.examenp.recliclerview.data.network.repository.RetrofitBase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllNobelPrizesUserCase {

    suspend fun invoke(limit: Int): Result<List<NobelPrizeX>> {
        var result: Result<List<NobelPrizeX>>? = null

        val baseService = RetrofitBase.getNobelPrizesConnection()
        val service = baseService.create(NobelPrizeEndPoint::class.java)
        val call = service.getAllNobelPrizes()

        try {
            if (call.isSuccessful) {
                val a = call.body()!!
                val nobelPrizes = a.nobelPrizes
                result = Result.success(nobelPrizes)
            } else {
                val msg = "Error en el llamado a la API de Jikan"
                result = Result.failure(Exception(msg))
                Log.d(Constants.TAG, msg)
            }
        } catch (ex: Exception) {
            Log.e(Constants.TAG, ex.stackTraceToString())
            result = Result.failure(ex)
        }
        return result!!
    }


}