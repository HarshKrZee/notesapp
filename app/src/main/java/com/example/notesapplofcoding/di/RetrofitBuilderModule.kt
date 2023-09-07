package com.example.notesapplofcoding.di

import com.example.notesapplofcoding.api.ApiInterface
import com.example.notesapplofcoding.api.RetrofitHelper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

fun provideApiService(): ApiInterface =
    Retrofit.Builder().baseUrl(RetrofitHelper.baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create()

val retrofitBuilderModule = module {
    single{ provideApiService() }
}