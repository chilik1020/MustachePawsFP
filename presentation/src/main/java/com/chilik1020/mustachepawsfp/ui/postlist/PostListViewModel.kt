package com.chilik1020.mustachepawsfp.ui.postlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.usecases.FetchPostsUseCase
import com.chilik1020.mustachepawsfp.mappers.PostDomainToPresentationModel
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import javax.inject.Inject
import kotlinx.coroutines.launch

class PostListViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var fetchPostUseCase: FetchPostsUseCase

    val toPresentationMapper = PostDomainToPresentationModel()

    private val viewStateMutable: MutableLiveData<PostListViewState> = MutableLiveData()
    val viewState: LiveData<PostListViewState>
        get() = viewStateMutable

    fun fetchPosts() {
        viewStateMutable.value = PostListViewState.Loading
        viewModelScope.launch {
            try {
                val posts = fetchPostUseCase.fetchPosts()
                    .map { toPresentationMapper.invoke(it) }
                    .toList()
                Log.d(LOG_TAG, "ViewModel: ${posts.toString()}")
                viewStateMutable.value = PostListViewState.Success(posts)
            } catch (ex: Exception) {
                Log.d(LOG_TAG, "ViewModelError: ${ex.message.toString()}")
                viewStateMutable.value = PostListViewState.Error(ex.message.toString())
            }
        }
    }
}