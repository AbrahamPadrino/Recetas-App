package com.example.recetasapp

import com.example.recetasapp.data.RecetasRepository
import com.example.recetasapp.data.RecetasService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
    @Singleton
    @Provides

    fun getRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("http://demo7618016.mockable.io/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun getGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesRecetaService(retrofit: Retrofit) = retrofit.create(RecetasService::class.java)

    @Singleton
    @Provides
    fun providesRecetasRepository(service: RecetasService) = RecetasRepository(service)
}