package com.chilik1020.mustachepawsfp.ui.postcreate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.models.PostLocation
import com.chilik1020.domain.models.PostRequestObject
import com.chilik1020.domain.usecases.CreatePostUseCase
import com.chilik1020.domain.usecases.UploadImageUseCase
import com.chilik1020.domain.usecases.YourProfileDetailsUseCase
import com.chilik1020.mustachepawsfp.mappers.PostDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.models.LocationPresentationModel
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import javax.inject.Inject
import kotlinx.coroutines.launch

class PostCreateViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var createPostUseCase: CreatePostUseCase

    @Inject
    lateinit var uploadImageUseCase: UploadImageUseCase

    @Inject
    lateinit var yourProfileDetailsUseCase: YourProfileDetailsUseCase

    @Inject
    lateinit var toPresentationMapper: PostDomainToPresentationMapper

    private val viewStateMutable: MutableLiveData<PostCreateViewState> = MutableLiveData()
    val viewState: LiveData<PostCreateViewState>
        get() = viewStateMutable

    val imageUri = MutableLiveData<String>()
    val typeOfAssist = MutableLiveData<String>()
    val typeOfAnimal = MutableLiveData<String>()
    val location = MutableLiveData<LocationPresentationModel>()
    val description = MutableLiveData<String>()

    fun createPost() {
        viewStateMutable.value = PostCreateViewState.Loading
        viewModelScope.launch {

            val locationPost = PostLocation(
                location.value?.lat ?: 20.0,
                location.value?.lng ?: 55.0,
                location.value?.description ?: "location undefined"
            )

            val creatorUsername = yourProfileDetailsUseCase.userDetails().username
            val imageFullPath = imageUri.value.toString()
            val index = imageFullPath.indexOfLast { it == '/' }
            val imageName = imageFullPath.substring(index + 1)
            Log.d(LOG_TAG, "imageName $imageName")
            val post = PostRequestObject(
                animalType = typeOfAnimal.value.toString(),
                assistType = typeOfAssist.value.toString(),
                location = locationPost,
                imageLink = imageName,
                creatorUsername = creatorUsername,
                description = description.value.toString()
            )
            try {
                uploadImageUseCase.uploadImage(
                    imageFullPath
                )
                val postCreated = createPostUseCase.createPost(post, post.imageLink)
                viewStateMutable.value =
                    PostCreateViewState.Success(toPresentationMapper.invoke(postCreated))
            } catch (ex: Exception) {
                Log.d(LOG_TAG, "POSTCREATEVIEWMODEL ERROR: ${ex.message}")
                viewStateMutable.value = PostCreateViewState.Error(ex.message.toString())
            }
        }
    }
}