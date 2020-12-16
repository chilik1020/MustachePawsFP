package com.chilik1020.domain.usecase

interface PersistAccessTokenUseCase {
    suspend fun persistAccessToken(token: String)
}