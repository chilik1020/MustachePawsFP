package com.chilik1020.mustachepawsfp.mappers

import com.chilik1020.domain.models.UserDomainModel
import com.chilik1020.mustachepawsfp.models.UserPresentationModel

class UserDomainToPresentationModel : (UserDomainModel) -> UserPresentationModel {
    override fun invoke(user: UserDomainModel): UserPresentationModel {
        return UserPresentationModel(
            username = user.username,
            firstname = user.firstname,
            lastname = user.lastname,
            email = user.email,
            phoneNumber = user.phoneNumber,
            createdAt = user.createdAt
        )
    }
}