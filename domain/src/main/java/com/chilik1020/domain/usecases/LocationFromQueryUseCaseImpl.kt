package com.chilik1020.domain.usecases

import com.chilik1020.domain.repositories.LocationRepository

class LocationFromQueryUseCaseImpl(private val locationRepository: LocationRepository) :
    LocationFromQueryUseCase {
    override suspend fun getLocation(query: String) {
        locationRepository.getLocationFromQuery(query)
    }
}