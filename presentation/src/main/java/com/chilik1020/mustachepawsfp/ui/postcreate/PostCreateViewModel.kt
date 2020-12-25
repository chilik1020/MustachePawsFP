package com.chilik1020.mustachepawsfp.ui.postcreate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PostCreateViewModel @Inject constructor() : ViewModel() {

    val imageUri = MutableLiveData<String>()
    val typeOfHelp = MutableLiveData<String>()
    val typeOfAnimal = MutableLiveData<String>()
    val location = MutableLiveData<String>()

    fun createPost() {

    }
}