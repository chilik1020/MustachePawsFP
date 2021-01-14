package com.chilik1020.data.mappers

import com.chilik1020.data.models.MapQuestLocationResponse
import com.chilik1020.domain.models.LocationDomainModel
import javax.inject.Inject

class LocationDataToDomainMapper @Inject constructor() :
        (MapQuestLocationResponse) -> LocationDomainModel {
    override fun invoke(res: MapQuestLocationResponse) = LocationDomainModel(
        description = "${res.results[0].locations[0].adminArea5}, ${res.results[0].locations[0].adminArea6}",
        lat = res.results[0].locations[0].latLng.lat,
        lng = res.results[0].locations[0].latLng.lng
    )
}