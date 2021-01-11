package com.chilik1020.mustachepawsfp.di

import android.content.SharedPreferences
import com.chilik1020.data.mappers.UserDataToDomainMapper
import com.chilik1020.data.mappers.UserDomainToDataMapper
import com.chilik1020.data.models.UserDataModel
import com.chilik1020.data.repositories.UserRepositoryImpl
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.data.sources.UserRemoteDataSource
import com.chilik1020.domain.models.UserDomainModel
import com.chilik1020.domain.repositories.UserRepository
import com.chilik1020.domain.usecases.*
import com.chilik1020.framework.local.UserLocalDataSourceImpl
import com.chilik1020.framework.remote.MustachePawsApi
import com.chilik1020.framework.remote.UserRemoteDataSourceImpl
import com.chilik1020.mustachepawsfp.mappers.UserDomainToPresentationMapper
import com.chilik1020.mustachepawsfp.models.UserPresentationModel
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun provideLoginUseCase(userRepository: UserRepository): LoginUseCase =
        LoginUseCaseImpl(userRepository)

    @Provides
    fun provideSignUpUseCase(userRepository: UserRepository): SignUpUseCase =
        SignUpUseCaseImpl(userRepository)

    @Provides
    fun provideYourUserDetailsUseCase(userRepository: UserRepository): YourProfileDetailsUseCase =
        YourProfileDetailsUseCaseImpl(userRepository)

    @Provides
    fun provideSaveProfileUseCase(userRepository: UserRepository): SaveProfileUseCase =
        SaveProfileUseCaseImpl(userRepository)

    @Provides
    fun provideUserProfileByIdUseCase(userRepository: UserRepository): UserProfileByIdUseCase =
        UserProfileByIdUseCaseImpl(userRepository)

    @Provides
    fun provideUserRepository(
        userLocalDataSource: UserLocalDataSource,
        userRemoteDataSource: UserRemoteDataSource,
        toDomainMapper: UserDataToDomainMapper,
        toDataMapper: UserDomainToDataMapper
    ): UserRepository = UserRepositoryImpl(
        userLocalDataSource, userRemoteDataSource, toDomainMapper, toDataMapper
    )

    @Provides
    fun provideUserLocalDataSource(preferences: SharedPreferences): UserLocalDataSource =
        UserLocalDataSourceImpl(preferences)

    @Provides
    fun provideUserRemoteDataSource(api: MustachePawsApi): UserRemoteDataSource =
        UserRemoteDataSourceImpl(api)

    @Provides
    fun provideUserDomainToDataMapper(): (UserDomainModel) -> UserDataModel =
        UserDomainToDataMapper()

    @Provides
    fun provideUserDataToDomainMapper(): (UserDataModel) -> UserDomainModel =
        UserDataToDomainMapper()

    @Provides
    fun provideUserDomainToPresentationMapper(): (UserDomainModel) -> UserPresentationModel =
        UserDomainToPresentationMapper()
}