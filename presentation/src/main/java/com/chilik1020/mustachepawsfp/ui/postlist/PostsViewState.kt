package com.chilik1020.mustachepawsfp.ui.postlist

import com.chilik1020.mustachepawsfp.models.PostPresentationModel

sealed class PostsViewState {
    object Loading : PostsViewState()
    class Success(val data: List<PostPresentationModel>) : PostsViewState()
    class Error(val msg: String) : PostsViewState()
}