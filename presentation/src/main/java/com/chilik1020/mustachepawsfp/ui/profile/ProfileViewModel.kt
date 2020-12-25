package com.chilik1020.mustachepawsfp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.usecases.YourProfileDetailsUseCase
import com.chilik1020.mustachepawsfp.mappers.UserDomainToPresentationModel
import javax.inject.Inject
import kotlinx.coroutines.launch

class ProfileViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var yourProfileDetailsUseCase: YourProfileDetailsUseCase

    @Inject
    lateinit var toPresentationModel: UserDomainToPresentationModel

    private val viewStateMutable: MutableLiveData<ProfileViewState> = MutableLiveData()
    val viewState: LiveData<ProfileViewState>
        get() = viewStateMutable

    fun userDetails() {
        viewStateMutable.value = ProfileViewState.Loading
        viewModelScope.launch {
            try {
                val user = yourProfileDetailsUseCase.userDetails()
                viewStateMutable.value = ProfileViewState.Success(toPresentationModel(user))
            } catch (ex: Exception) {
                viewStateMutable.value = ProfileViewState.Error(ex.message.toString())
            }
        }
    }
}