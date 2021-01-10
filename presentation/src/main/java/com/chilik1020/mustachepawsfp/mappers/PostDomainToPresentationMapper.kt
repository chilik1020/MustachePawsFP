package com.chilik1020.mustachepawsfp.mappers

import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.mustachepawsfp.models.PostPresentationModel
import javax.inject.Inject

class PostDomainToPresentationMapper @Inject constructor() :
        (PostDomainModel) -> PostPresentationModel {
    override fun invoke(post: PostDomainModel) = PostPresentationModel(
        id = post.id,
        closed = post.closed,
        typeOfAnimal = post.typeOfAnimal,
        typeOfAssist = post.typeOfHelp,
        location = post.location,
        description = post.description,
        imageLink = post.imageLink,
        creatorUsername = post.creatorUsername,
        createdAt = post.createdAt
    )
}