package com.chilik1020.mustachepawsfp.ui.postlist

import com.chilik1020.mustachepawsfp.models.PostPresentationModel

sealed class PostListViewState {
    object Loading : PostListViewState()
    class Success(val data: List<PostPresentationModel>) : PostListViewState()
    class Error(val msg: String) : PostListViewState()
}