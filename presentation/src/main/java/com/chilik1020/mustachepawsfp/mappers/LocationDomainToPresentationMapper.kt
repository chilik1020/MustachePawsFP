package com.chilik1020.mustachepawsfp.mappers

import com.chilik1020.domain.models.LocationDomainModel
import com.chilik1020.mustachepawsfp.models.LocationPresentationModel
import javax.inject.Inject

class LocationDomainToPresentationMapper @Inject constructor() :
        (LocationDomainModel) -> LocationPresentationModel {
    override fun invoke(location: LocationDomainModel) = LocationPresentationModel(
        description = location.description,
        lat = location.lat,
        lng = location.lng
    )
}