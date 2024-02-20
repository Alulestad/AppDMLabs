package com.examenp.recliclerview.logic.usercase.nobel

import android.util.Log
import com.examenp.recliclerview.core.Constants
import com.examenp.recliclerview.data.network.endpoints.nobel.NobelPrizeEndPoint
import com.examenp.recliclerview.data.network.entities.nobel.NobelPrize
import com.examenp.recliclerview.data.network.entities.nobel.NobelPrizeX
import com.examenp.recliclerview.data.network.repository.KtorApiModule
import com.examenp.recliclerview.data.network.repository.RetrofitBase
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.isSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllNobelPrizesKtor {

    suspend fun invoke(limit:Int): Flow<Result<List<NobelPrizeX>>> = flow {
        var ret:List<NobelPrizeX> = ArrayList()

        var result: Result<List<NobelPrizeX>>? = null
        var newLimit=1

        while (newLimit<limit) {
            kotlin.runCatching {
                KtorApiModule.getKtorHttpClient().get {
                    url(RetrofitBase.NOBEL_PRIZES + "nobelPrizes?limit=$newLimit")
                }
            }.onSuccess {
                if (it.status.isSuccess()) {

                    ret = it.body<NobelPrize>().nobelPrizes!!
                    result = Result.success(ret)

                }

            }.onFailure {
                Log.d("TAG", it.message.toString())
            }

            emit(result!!)
            delay(2000)
            newLimit++

        }





    }

}