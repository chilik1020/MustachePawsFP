package com.chilik1020.framework.local

interface ApiKeyProvider {
    fun provideGoogleMapsApiKey(): String
    fun provideMapQuestApiKey(): String
}