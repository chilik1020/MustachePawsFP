package com.chilik1020.data.sources

import com.chilik1020.data.models.MapQuestLocationResponse

interface LocationRemoteDataSource {
    suspend fun getLocationFromQuery(query: String): MapQuestLocationResponse
}