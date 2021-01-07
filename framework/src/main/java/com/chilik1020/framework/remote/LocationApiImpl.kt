package com.chilik1020.framework.remote

import android.util.Log
import com.chilik1020.data.models.MapQuestLocationResponse
import com.chilik1020.framework.utils.ACCESS_TOKEN_MAP_QUEST_COM
import com.chilik1020.framework.utils.LOCATION_FROM_QUERY_BASE_URL
import com.chilik1020.framework.utils.LOG_TAG
import com.google.gson.GsonBuilder
import java.io.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class LocationApiImpl(
    private val okHttpClient: OkHttpClient
) : LocationApi {
    override suspend fun getLocationFromUserQuery(query: String) {
        val client = OkHttpClient()
        val url =
            LOCATION_FROM_QUERY_BASE_URL.format(ACCESS_TOKEN_MAP_QUEST_COM, query)
        val request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                Log.d(LOG_TAG, "BODY : $body")
                val gson = GsonBuilder().create()
                val result = gson.fromJson(body, MapQuestLocationResponse::class.java)
                Log.d(LOG_TAG, "LocationResponse: $result")
                Log.d(LOG_TAG, "LocationResponseResults: ${result.results}")
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d(LOG_TAG, "LocationResponse: ${e.message.toString()}")
            }
        })
    }
}