package com.chilik1020.domain.repositories

interface LocationRepository {
    suspend fun getLocationFromQuery(query: String)
}