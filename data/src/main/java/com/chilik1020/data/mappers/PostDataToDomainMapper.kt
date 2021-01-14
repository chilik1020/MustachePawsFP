package com.chilik1020.data.mappers

import com.chilik1020.data.models.PostDataModel
import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.models.PostLocation
import javax.inject.Inject

class PostDataToDomainMapper @Inject constructor() : (PostDataModel) -> PostDomainModel {
    override fun invoke(post: PostDataModel) = PostDomainModel(
        id = post.id,
        closed = post.closed,
        typeOfAnimal = post.animalType,
        typeOfHelp = post.assistType,
        location = PostLocation(
            lon = post.location.lon,
            lat = post.location.lat,
            description = post.location.description
        ),
        description = post.description,
        imageLink = post.imageLink,
        creatorUsername = post.creatorUsername,
        createdAt = post.createdAt
    )
}