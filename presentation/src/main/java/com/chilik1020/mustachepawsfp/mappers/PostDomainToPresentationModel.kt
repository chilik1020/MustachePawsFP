package com.chilik1020.mustachepawsfp.mappers

import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.mustachepawsfp.models.PostPresentationModel

class PostDomainToPresentationModel : (PostDomainModel) -> PostPresentationModel {
    override fun invoke(post: PostDomainModel) = PostPresentationModel(
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