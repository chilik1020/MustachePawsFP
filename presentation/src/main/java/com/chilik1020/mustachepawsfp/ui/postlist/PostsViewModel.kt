package com.chilik1020.mustachepawsfp.ui.postlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.usecases.FetchPostsUseCase
import com.chilik1020.mustachepawsfp.mappers.PostDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var fetchPostUseCase: FetchPostsUseCase

    @Inject
    lateinit var toPresentationMapper: PostDomainToPresentationMapper

    private val viewStateMutable: MutableLiveData<PostsViewState> = MutableLiveData()
    val viewState: LiveData<PostsViewState>
        get() = viewStateMutable

    fun fetchPosts() {
        viewStateMutable.value = PostsViewState.Loading
        viewModelScope.launch {
            try {
                val posts = fetchPostUseCase.fetchPosts().map {
                    toPresentationMapper.invoke(it)
                }
                viewStateMutable.value = PostsViewState.Success(posts)
            } catch (ex: Exception) {
                Log.d(LOG_TAG, "ViewModelError: ${ex.message.toString()}")
                viewStateMutable.value = PostsViewState.Error(ex.message.toString())
            }
        }
    }
}