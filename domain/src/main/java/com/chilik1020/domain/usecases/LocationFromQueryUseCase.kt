package com.chilik1020.domain.usecases

interface LocationFromQueryUseCase {
    suspend fun getLocation(query: String)
}