package com.chilik1020.framework.remote

interface LocationApi {
    suspend fun getLocationFromUserQuery(query: String)
}