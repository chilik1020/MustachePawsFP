package com.chilik1020.data.mappers

import com.chilik1020.data.models.PostDataModel
import com.chilik1020.domain.models.PostDomainModel
import javax.inject.Inject

class PostDataToDomainMapper @Inject constructor() : (PostDataModel) -> PostDomainModel {
    override fun invoke(post: PostDataModel) = PostDomainModel(
        id = post.id,
        closed = post.closed,
        typeOfAnimal = post.typeOfAnimal,
        ageOfAnimal = post.ageOfAnimal,
        typeOfHelp = post.typeOfHelp,
        description = post.description,
        imageLink = post.imageLink,
        creatorUsername = post.creatorUsername,
        createdAt = post.createdAt
    )
}