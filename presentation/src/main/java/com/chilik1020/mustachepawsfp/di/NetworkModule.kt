package com.chilik1020.mustachepawsfp.di

import com.chilik1020.framework.remote.LocationApi
import com.chilik1020.framework.remote.LocationApiImpl
import com.chilik1020.framework.remote.MustachePawsApi
import com.chilik1020.framework.utils.BASE_URL
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMustachePawsApi(retrofit: Retrofit): MustachePawsApi =
        retrofit.create(MustachePawsApi::class.java)

    @Provides
    @Singleton
    fun provideLocationApi(okHttpClient: OkHttpClient): LocationApi =
        LocationApiImpl(okHttpClient)

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
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