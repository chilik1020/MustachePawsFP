package com.chilik1020.framework.local

import com.chilik1020.framework.BuildConfig

class ApiKeyProvideImpl : ApiKeyProvider {
    override fun provideGoogleMapsApiKey() = ""
    override fun provideMapQuestApiKey() = BuildConfig.MAP_QUEST_API_KEY
}