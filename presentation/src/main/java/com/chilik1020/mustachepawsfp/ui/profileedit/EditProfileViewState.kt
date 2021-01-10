package com.chilik1020.mustachepawsfp.ui.profileedit

sealed class EditProfileViewState {
    object Loading : EditProfileViewState()
    object Success : EditProfileViewState()
    class Error(val msg: String) : EditProfileViewState()
}