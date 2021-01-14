package com.chilik1020.mustachepawsfp.di

import com.chilik1020.data.mappers.LocationDataToDomainMapper
import com.chilik1020.data.mappers.PostDataToDomainMapper
import com.chilik1020.data.models.MapQuestLocationResponse
import com.chilik1020.data.models.PostDataModel
import com.chilik1020.data.repositories.ImageRepositoryImpl
import com.chilik1020.data.repositories.LocationRepositoryImpl
import com.chilik1020.data.repositories.PostRepositoryImpl
import com.chilik1020.data.sources.ImageRemoteDataSource
import com.chilik1020.data.sources.LocationRemoteDataSource
import com.chilik1020.data.sources.PostRemoteDataSource
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.domain.models.LocationDomainModel
import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.repositories.ImageRepository
import com.chilik1020.domain.repositories.LocationRepository
import com.chilik1020.domain.repositories.PostRepository
import com.chilik1020.domain.usecases.CreatePostUseCase
import com.chilik1020.domain.usecases.CreatePostUseCaseImpl
import com.chilik1020.domain.usecases.FetchPostByCreatorIdUseCase
import com.chilik1020.domain.usecases.FetchPostByCreatorIdUseCaseImpl
import com.chilik1020.domain.usecases.FetchPostByIdUseCase
import com.chilik1020.domain.usecases.FetchPostByIdUseCaseImpl
import com.chilik1020.domain.usecases.FetchPostsUseCase
import com.chilik1020.domain.usecases.FetchPostsUseCaseImpl
import com.chilik1020.domain.usecases.LocationFromQueryUseCase
import com.chilik1020.domain.usecases.LocationFromQueryUseCaseImpl
import com.chilik1020.domain.usecases.UploadImageUseCase
import com.chilik1020.domain.usecases.UploadImageUseCaseImpl
import com.chilik1020.framework.remote.ImageRemoteDataSourceImpl
import com.chilik1020.framework.remote.LocationApi
import com.chilik1020.framework.remote.LocationRemoteDataSourceImpl
import com.chilik1020.framework.remote.MustachePawsApi
import com.chilik1020.framework.remote.PostRemoteDataSourceImpl
import com.chilik1020.mustachepawsfp.mappers.LocationDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.mappers.PostDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.models.LocationPresentationModel
import com.chilik1020.mustachepawsfp.models.PostPresentationModel
import dagger.Module
import dagger.Provides

@Module
class PostModule {

    @Provides
    fun provideFetchPostsUseCase(postRepository: PostRepository): FetchPostsUseCase =
        FetchPostsUseCaseImpl(postRepository)

    @Provides
    fun provideFetchPostByIdUseCase(postRepository: PostRepository): FetchPostByIdUseCase =
        FetchPostByIdUseCaseImpl(postRepository)

    @Provides
    fun provideFetchPostByCreatorIdUseCase(postRepository: PostRepository): FetchPostByCreatorIdUseCase =
        FetchPostByCreatorIdUseCaseImpl(postRepository)

    @Provides
    fun provideCreatePostUseCase(postRepository: PostRepository): CreatePostUseCase =
        CreatePostUseCaseImpl(postRepository)

    @Provides
    fun provideLocationFromQueryUseCase(locationRepository: LocationRepository): LocationFromQueryUseCase =
        LocationFromQueryUseCaseImpl(locationRepository)

    @Provides
    fun provideUploadImageUseCase(imageRepository: ImageRepository): UploadImageUseCase =
        UploadImageUseCaseImpl(imageRepository)

    @Provides
    fun providePostRepository(
        userLocalDataSource: UserLocalDataSource,
        postRemoteDataSource: PostRemoteDataSource,
        toDomainMapper: PostDataToDomainMapper
    ): PostRepository =
        PostRepositoryImpl(userLocalDataSource, postRemoteDataSource, toDomainMapper)

    @Provides
    fun provideImageRepository(
        imageRemoteDataSource: ImageRemoteDataSource,
        userLocalDataSource: UserLocalDataSource
    ): ImageRepository = ImageRepositoryImpl(imageRemoteDataSource, userLocalDataSource)

    @Provides
    fun provideLocationRepository(
        locationRemoteDataSource: LocationRemoteDataSource,
        locationDataToDomainMapper: LocationDataToDomainMapper
    ): LocationRepository =
        LocationRepositoryImpl(locationRemoteDataSource, locationDataToDomainMapper)

    @Provides
    fun providePostRemoteDataSource(api: MustachePawsApi): PostRemoteDataSource =
        PostRemoteDataSourceImpl(api)

    @Provides
    fun provideImageRemoteDataSource(api: MustachePawsApi): ImageRemoteDataSource =
        ImageRemoteDataSourceImpl(api)

    @Provides
    fun provideLocationRemoteDataSource(api: LocationApi): LocationRemoteDataSource =
        LocationRemoteDataSourceImpl(api)

    @Provides
    fun providePostDataToDomainMapper(): (PostDataModel) -> PostDomainModel =
        PostDataToDomainMapper()

    @Provides
    fun providePostDomainToPresentationMapper(): (PostDomainModel) -> PostPresentationModel =
        PostDomainToPresentationMapper()

    @Provides
    fun provideLocationDataToDomainMapper(): (MapQuestLocationResponse) -> LocationDomainModel =
        LocationDataToDomainMapper()

    @Provides
    fun provideLocationDomainToPresentationMapper(): (LocationDomainModel) -> LocationPresentationModel =
        LocationDomainToPresentationMapper()
}