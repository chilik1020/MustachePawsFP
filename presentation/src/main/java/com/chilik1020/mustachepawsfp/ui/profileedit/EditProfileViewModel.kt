package com.chilik1020.mustachepawsfp.ui.profileedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chilik1020.domain.usecases.UploadImageUseCase
import javax.inject.Inject

class EditProfileViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var uploadImageUseCase: UploadImageUseCase

    private val editProfileViewStateMutable: MutableLiveData<EditProfileViewState> =
        MutableLiveData()
    val userDetailsViewState: LiveData<EditProfileViewState>
        get() = editProfileViewStateMutable

    fun saveProfile() {

    }
}