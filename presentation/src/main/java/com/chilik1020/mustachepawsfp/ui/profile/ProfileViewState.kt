package com.chilik1020.mustachepawsfp.ui.profile

import com.chilik1020.mustachepawsfp.models.UserPresentationModel

sealed class ProfileViewState {
    object Loading : ProfileViewState()
    class Success(val data: UserPresentationModel) : ProfileViewState()
    class Error(val msg: String) : ProfileViewState()
}