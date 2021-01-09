package com.chilik1020.mustachepawsfp.ui.postcreate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val locationLiveDataMutable = MutableLiveData<LocationPresentationModel>()
    val locationLiveData: LiveData<LocationPresentationModel>
        get() = locationLiveDataMutable

    fun getLocationFromQuery(query: String) {
        viewModelScope.launch {
            val locationDomain = locationFromQueryUseCase.getLocation(query)
            locationLiveDataMutable.value = locationDomainToPresentationMapper(locationDomain)
        }
    }
}