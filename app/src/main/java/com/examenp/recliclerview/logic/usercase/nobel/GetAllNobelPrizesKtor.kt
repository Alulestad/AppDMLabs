package com.examenp.recliclerview.logic.usercase.nobel

import android.util.Log
import com.examenp.recliclerview.data.network.entities.nobel.NobelPrize
import com.examenp.recliclerview.data.network.entities.nobel.NobelPrizeX
import com.examenp.recliclerview.data.network.repository.KtorApiModule
import com.examenp.recliclerview.data.network.repository.RetrofitBase
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.isSuccess

class GetAllNobelPrizesKtor {

    suspend fun invoke():List<NobelPrizeX>{
        var ret:List<NobelPrizeX> = ArrayList()

        kotlin.runCatching {
            KtorApiModule.getKtorHttpClient().get {
                url(RetrofitBase.NOBEL_PRIZES+ "nobelPrizes")
            }
        }.onSuccess {
            if (it.status.isSuccess()){
                ret = it.body<NobelPrize>().nobelPrizes!!
            }

        }.onFailure {
            Log.d("TAG",it.message.toString())
        }

        return ret
    }

}