package com.chilik1020.framework.remote

import com.chilik1020.data.sources.LocationRemoteDataSource

class LocationRemoteDataSourceImpl(
    private val locationApi: LocationApi
) : LocationRemoteDataSource {
    override suspend fun getLocationFromQuery(query: String) {
        locationApi.getLocationFromUserQuery(query)
    }
}