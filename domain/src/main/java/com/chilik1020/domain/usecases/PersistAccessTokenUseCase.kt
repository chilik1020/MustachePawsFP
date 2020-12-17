package com.chilik1020.domain.usecases

interface PersistAccessTokenUseCase {
    suspend fun persistAccessToken(token: String)
}