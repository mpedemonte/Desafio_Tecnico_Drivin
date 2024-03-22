package com.mpdev.razasperrosdrivin.di

import com.mpdev.razasperrosdrivin.data.network.breeds.BreedsApiClient
import com.mpdev.razasperrosdrivin.data.network.img_breeds.ImageBreedApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Proporciona una instancia única de Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Proporciona una instancia única de BreedsApiClient utilizando Retrofit
    @Singleton
    @Provides
    fun provideBreedsApiClient(retrofit: Retrofit): BreedsApiClient {
        return retrofit.create(BreedsApiClient::class.java)
    }

    // Proporciona una instancia única de ImageBreedApiClient utilizando Retrofit
    @Singleton
    @Provides
    fun provideImageBreedApiClient(retrofit: Retrofit): ImageBreedApiClient{
        return retrofit.create(ImageBreedApiClient::class.java)
    }

}