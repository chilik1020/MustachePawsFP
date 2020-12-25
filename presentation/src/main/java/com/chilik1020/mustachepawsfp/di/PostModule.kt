package com.chilik1020.mustachepawsfp.di

import com.chilik1020.data.mappers.PostDataToDomainMapper
import com.chilik1020.data.models.PostDataModel
import com.chilik1020.data.repositories.PostRepositoryImpl
import com.chilik1020.data.sources.PostRemoteDataSource
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.repositories.PostRepository
import com.chilik1020.domain.usecases.FetchPostsUseCase
import com.chilik1020.domain.usecases.FetchPostsUseCaseImpl
import com.chilik1020.framework.remote.MustachePawsApi
import com.chilik1020.framework.remote.PostRemoteDataSourceImpl
import com.chilik1020.mustachepawsfp.mappers.PostDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.models.PostPresentationModel
import dagger.Module
import dagger.Provides

@Module
class PostModule {

    @Provides
    fun provideFetchPostsUseCase(postRepository: PostRepository): FetchPostsUseCase =
        FetchPostsUseCaseImpl(postRepository)

    @Provides
    fun providePostRepository(
        userLocalDataSource: UserLocalDataSource,
        postRemoteDataSource: PostRemoteDataSource,
        toDomainMapper: PostDataToDomainMapper
    ): PostRepository =
        PostRepositoryImpl(userLocalDataSource, postRemoteDataSource, toDomainMapper)

    @Provides
    fun providePostRemoteDataSource(api: MustachePawsApi):PostRemoteDataSource =
        PostRemoteDataSourceImpl(api)

    @Provides
    fun providePostDataToDomainMapper(): (PostDataModel) -> PostDomainModel =
        PostDataToDomainMapper()

    @Provides
    fun providePostDomainToPresentationMapper(): (PostDomainModel) -> PostPresentationModel =
        PostDomainToPresentationMapper()
}