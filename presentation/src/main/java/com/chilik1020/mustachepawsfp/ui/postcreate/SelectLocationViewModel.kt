package com.chilik1020.mustachepawsfp.ui.postcreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.usecases.LocationFromQueryUseCase
import com.chilik1020.mustachepawsfp.mappers.LocationDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.models.LocationPresentationModel
import javax.inject.Inject
import kotlinx.coroutines.launch

class SelectLocationViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var locationFromQueryUseCase: LocationFromQueryUseCase

    @Inject
    lateinit var locationDomainToPresentationMapper: LocationDomainToPresentationMapper

    var location: LocationPresentationModel? = null

    fun getLocationFromQuery(query: String) {
        viewModelScope.launch {
            val locationDomain = locationFromQueryUseCase.getLocation(query)
            location = locationDomainToPresentationMapper(locationDomain)
        }
    }
}