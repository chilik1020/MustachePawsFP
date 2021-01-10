package com.chilik1020.mustachepawsfp.ui.profileedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.usecases.UploadImageUseCase
import com.chilik1020.domain.usecases.YourProfileDetailsUseCase
import com.chilik1020.mustachepawsfp.mappers.UserDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.models.UserPresentationModel
import javax.inject.Inject
import kotlinx.coroutines.launch

class EditProfileViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var yourProfileDetailsUseCase: YourProfileDetailsUseCase

    @Inject
    lateinit var uploadImageUseCase: UploadImageUseCase

    @Inject
    lateinit var toPresentationMapper: UserDomainToPresentationMapper

    private val editProfileViewStateMutable: MutableLiveData<EditProfileViewState> =
        MutableLiveData()
    val userDetailsViewState: LiveData<EditProfileViewState>
        get() = editProfileViewStateMutable

    private val currentProfileMutableLD: MutableLiveData<UserPresentationModel> = MutableLiveData()
    val currentProfileLD: LiveData<UserPresentationModel>
        get() = currentProfileMutableLD

    fun getCurrentProfile() {
        viewModelScope.launch {
            val user = toPresentationMapper(yourProfileDetailsUseCase.userDetails())
            currentProfileMutableLD.value = user
        }
    }

    fun saveProfile() {

    }
}