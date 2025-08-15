package com.example.medicaladminapp.di

import com.example.medicaladminapp.api.ApiBuilder
import com.example.medicaladminapp.api.ApiServices
import com.example.medicaladminapp.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModule {


    @Provides
    @Singleton
    fun provideApi() : ApiBuilder {
        return ApiBuilder
    }

    @Provides
    @Singleton
    fun provideRepo(apiService: ApiBuilder
    ) : Repo {
        return Repo(apiService)
    }


}
