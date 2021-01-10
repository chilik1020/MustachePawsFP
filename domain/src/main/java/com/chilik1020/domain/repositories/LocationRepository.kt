package com.chilik1020.domain.repositories

import com.chilik1020.domain.models.LocationDomainModel

interface LocationRepository {
    suspend fun getLocationFromQuery(query: String): LocationDomainModel
}