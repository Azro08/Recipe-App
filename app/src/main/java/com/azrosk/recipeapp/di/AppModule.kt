package com.azrosk.recipeapp.di

import com.azrosk.recipeapp.api.ApiCategoryService
import com.azrosk.recipeapp.api.ApiMealService
import com.azrosk.recipeapp.helper.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofitInstance(BASE_URL : String) : ApiCategoryService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiCategoryService::class.java)

    @Singleton
    @Provides
    fun provideRetrofitMealInstance(BASE_URL: String) : ApiMealService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiMealService::class.java)


}