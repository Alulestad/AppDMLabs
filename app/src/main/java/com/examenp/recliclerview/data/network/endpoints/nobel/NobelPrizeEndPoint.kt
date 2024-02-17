package com.examenp.recliclerview.data.network.endpoints.nobel


import com.examenp.recliclerview.data.network.entities.nobel.NobelPrize;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query

interface NobelPrizeEndPoint {
    @GET("nobelPrizes")
    //suspend fun getAllNobelPrizes(@Query("limit") limit: Int): Response<NobelPrize>
    suspend fun getAllNobelPrizes(): Response<NobelPrize>
}
