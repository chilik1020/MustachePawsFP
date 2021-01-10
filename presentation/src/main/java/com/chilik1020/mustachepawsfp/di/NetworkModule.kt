package com.chilik1020.mustachepawsfp.di

import com.chilik1020.framework.remote.LocationApi
import com.chilik1020.framework.remote.MustachePawsApi
import com.chilik1020.framework.utils.LOCATION_FROM_QUERY_BASE_URL
import com.chilik1020.framework.utils.MUSTACHE_BASE_URL
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Qualifier
annotation class Mustache

@Qualifier
annotation class MapQuestLocation

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMustachePawsApi(@Mustache retrofit: Retrofit): MustachePawsApi =
        retrofit.create(MustachePawsApi::class.java)

    @Provides
    @Singleton
    fun provideLocationApi(@MapQuestLocation retrofit: Retrofit): LocationApi =
        retrofit.create(LocationApi::class.java)

    @Provides
    @Mustache
    @Singleton
    fun provideMustacheRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(MUSTACHE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @MapQuestLocation
    @Singleton
    fun provideMapQuestRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(LOCATION_FROM_QUERY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}