package com.chilik1020.data.repositories

import com.chilik1020.data.models.MapQuestLocationResponse
import com.chilik1020.data.sources.LocationRemoteDataSource
import com.chilik1020.domain.models.LocationDomainModel
import com.chilik1020.domain.repositories.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationRepositoryImpl(
    private val remoteDataSource: LocationRemoteDataSource,
    private val locationDataToDomainMapper: (MapQuestLocationResponse) -> LocationDomainModel
) : LocationRepository {
    override suspend fun getLocationFromQuery(query: String) =
        withContext(Dispatchers.IO) {
            val response = remoteDataSource.getLocationFromQuery(query)
            return@withContext locationDataToDomainMapper(response)
        }
}