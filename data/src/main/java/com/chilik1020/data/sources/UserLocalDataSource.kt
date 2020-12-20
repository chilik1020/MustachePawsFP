package com.chilik1020.data.sources

import com.chilik1020.data.models.UserDataModel

interface UserLocalDataSource {
    suspend fun saveAccessToken(token: String)
    suspend fun getSavedToken(): String?
    suspend fun saveUserDetails(userDataModel: UserDataModel)
    suspend fun getSavedUserDetails(): UserDataModel?
}