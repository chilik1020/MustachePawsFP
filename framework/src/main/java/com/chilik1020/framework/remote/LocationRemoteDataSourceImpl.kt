package com.chilik1020.framework.remote

import android.util.Log
import com.chilik1020.data.sources.LocationRemoteDataSource
import com.chilik1020.framework.local.ApiKeyProvideImpl
import com.chilik1020.framework.utils.LOG_TAG

class LocationRemoteDataSourceImpl(
    private val locationApi: LocationApi
) : LocationRemoteDataSource {
    override suspend fun getLocationFromQuery(query: String) {
        val response =
            locationApi.getLocationFromUserQuery(ApiKeyProvideImpl().provideMapQuestApiKey(), query)
        Log.d(LOG_TAG, "LOCATION RESP $response")
    }
}