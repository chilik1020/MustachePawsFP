package com.chilik1020.mustachepawsfp.ui.postcreate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.models.PostLocation
import com.chilik1020.domain.models.PostRequestObject
import com.chilik1020.domain.usecases.CreatePostUseCase
import com.chilik1020.domain.usecases.LocationFromQueryUseCase
import com.chilik1020.domain.usecases.UploadImageUseCase
import com.chilik1020.mustachepawsfp.mappers.PostDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import com.google.android.libraries.maps.model.LatLng
import javax.inject.Inject
import kotlinx.coroutines.launch

class PostCreateViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var createPostUseCase: CreatePostUseCase

    @Inject
    lateinit var uploadImageUseCase: UploadImageUseCase

    @Inject
    lateinit var locationFromQueryUseCase: LocationFromQueryUseCase

    @Inject
    lateinit var toPresentationMapper: PostDomainToPresentationMapper

    private val viewStateMutable: MutableLiveData<PostCreateViewState> = MutableLiveData()
    val viewState: LiveData<PostCreateViewState>
        get() = viewStateMutable

    val imageUri = MutableLiveData<String>()
    val typeOfHelp = MutableLiveData<String>()
    val typeOfAnimal = MutableLiveData<String>()
    val location = MutableLiveData<LatLng>()
    val description = MutableLiveData<String>()

    fun createPost() {
        viewStateMutable.value = PostCreateViewState.Loading
        viewModelScope.launch {
            Log.d(LOG_TAG, "POSTCREATEVIEWMODEL scope.launch")
            val locationPost = PostLocation(
                20.0, 57.0, "Somewhere on Earth"
            )

            val post = PostRequestObject(
                animalType = "AnimalType 1",
                assistType = "AssistType 1",
                location = locationPost,
                imageLink = "img_cropped_20210101_191227_6462414432172716851.jpg",
                creatorUsername = "chilik1020",
                description = "description post"
            )

            try {
                uploadImageUseCase.uploadImage(
                    "/storage/emulated/0/Android/data/com.chilik1020.mustachepawsfp/files/Pictures/img_cropped_20210101_191227_6462414432172716851.jpg"
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

    fun getLocationFromQuery(query: String) {
        Log.d(LOG_TAG, "POSTCREATEVIEWMODEL getLocationFromQuery ")
        viewModelScope.launch {
            Log.d(LOG_TAG, "POSTCREATEVIEWMODEL getLocationFromQuery launch section")
            locationFromQueryUseCase.getLocation(query)
        }
    }
}