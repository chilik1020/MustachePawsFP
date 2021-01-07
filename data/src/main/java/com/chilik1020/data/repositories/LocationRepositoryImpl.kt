package com.chilik1020.data.repositories

import com.chilik1020.data.sources.LocationRemoteDataSource
import com.chilik1020.domain.repositories.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationRepositoryImpl(
    private val remoteDataSource: LocationRemoteDataSource
) : LocationRepository {
    override suspend fun getLocationFromQuery(query: String) {
        withContext(Dispatchers.IO) {
            remoteDataSource.getLocationFromQuery(query)
        }
    }
}