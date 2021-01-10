package com.chilik1020.data.mappers

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.domain.models.UserDomainModel
import javax.inject.Inject

class UserDomainToDataMapper @Inject constructor() : (UserDomainModel) -> UserDataModel {
    override fun invoke(user: UserDomainModel): UserDataModel {
        return UserDataModel(
            id = user.id,
            username = user.username,
            firstname = user.firstname,
            lastname = user.lastname,
            email = user.email,
            phoneNumber = user.phoneNumber,
            profileImage = user.profileImage,
            createdAt = user.createdAt
        )
    }
}