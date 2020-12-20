package com.chilik1020.data.mappers

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.domain.models.UserDomainModel
import javax.inject.Inject

class UserDataToDomainMapper @Inject constructor() : (UserDataModel) -> UserDomainModel {
    override fun invoke(user: UserDataModel): UserDomainModel {
        return UserDomainModel(
            id = user.id,
            username = user.username,
            firstname = user.firstname,
            lastname = user.lastname,
            email = user.email,
            phoneNumber = user.phoneNumber,
            createdAt = user.createdAt
        )
    }
}