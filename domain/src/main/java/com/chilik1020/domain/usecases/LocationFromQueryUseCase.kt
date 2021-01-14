package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.LocationDomainModel

interface LocationFromQueryUseCase {
    suspend fun getLocation(query: String) : LocationDomainModel
}