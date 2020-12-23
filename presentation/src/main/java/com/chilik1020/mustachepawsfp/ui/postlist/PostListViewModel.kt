package com.chilik1020.mustachepawsfp.ui.postlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.usecases.FetchPostsUseCase
import com.chilik1020.mustachepawsfp.mappers.PostDomainToPresentationModel
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
                viewStateMutable.value = PostListViewState.Success(posts)
            } catch (ex: Exception) {
                viewStateMutable.value = PostListViewState.Error(ex.message.toString())
            }
        }
    }
}