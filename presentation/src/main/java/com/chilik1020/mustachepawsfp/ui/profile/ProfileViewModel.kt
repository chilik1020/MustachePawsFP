package com.chilik1020.mustachepawsfp.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.usecases.FetchPostByCreatorIdUseCase
import com.chilik1020.domain.usecases.YourProfileDetailsUseCase
import com.chilik1020.mustachepawsfp.mappers.PostDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.mappers.UserDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import javax.inject.Inject
import kotlinx.coroutines.launch

class ProfileViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var yourProfileDetailsUseCase: YourProfileDetailsUseCase

    @Inject
    lateinit var fetchPostByCreatorIdUseCase: FetchPostByCreatorIdUseCase

    @Inject
    lateinit var toPresentationModel: UserDomainToPresentationMapper

    @Inject
    lateinit var postToPresentationMapper: PostDomainToPresentationMapper

    private val userDetailsViewStateMutable: MutableLiveData<ProfileViewState> = MutableLiveData()
    val userDetailsViewState: LiveData<ProfileViewState>
        get() = userDetailsViewStateMutable

    fun userDetails() {
        userDetailsViewStateMutable.value = ProfileViewState.Loading
        viewModelScope.launch {
            try {
                val user = yourProfileDetailsUseCase.userDetails()
                Log.d(LOG_TAG, "USERID = ${user.id}")
                val posts = fetchPostByCreatorIdUseCase.fetchPost(user.id)
                    .map { postToPresentationMapper.invoke(it) }
                userDetailsViewStateMutable.value =
                    ProfileViewState.Success(
                        toPresentationModel(user),
                        posts
                    )
            } catch (ex: Exception) {
                userDetailsViewStateMutable.value = ProfileViewState.Error(ex.message.toString())
            }
        }
    }
}