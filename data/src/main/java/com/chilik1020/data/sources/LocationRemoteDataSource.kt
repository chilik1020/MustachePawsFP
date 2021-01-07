package com.chilik1020.data.sources

interface LocationRemoteDataSource {
    suspend fun getLocationFromQuery(query: String)
}