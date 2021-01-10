package com.chilik1020.mustachepawsfp.ui.postcreate

import com.chilik1020.mustachepawsfp.models.PostPresentationModel

sealed class PostCreateViewState {
    object Loading : PostCreateViewState()
    class Success(val post: PostPresentationModel) : PostCreateViewState()
    class Error(val msg: String) : PostCreateViewState()
}