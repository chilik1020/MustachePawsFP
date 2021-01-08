package com.chilik1020.framework.remote

import com.chilik1020.data.models.MapQuestLocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("geocoding/v1/address")
    suspend fun getLocationFromUserQuery(
        @Query("key") apiKey: String,
        @Query("location") query: String
    ): MapQuestLocationResponse
}