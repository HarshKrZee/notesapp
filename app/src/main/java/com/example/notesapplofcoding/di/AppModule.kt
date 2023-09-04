package com.example.notesapplofcoding.di

import android.content.Context
import androidx.room.Room
import com.example.notesapplofcoding.api.ApiInterface
import com.example.notesapplofcoding.api.RetrofitHelper
import com.example.notesapplofcoding.db.NoteDao
import com.example.notesapplofcoding.db.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @Named("dbname")
    fun getDbName(): String = "Notes"

    @Singleton
    @Provides
    fun getRoomDb(@ApplicationContext context: Context,@Named("dbname") name: String): NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java,name).build()

    @Singleton
    @Provides
    fun getNoteDao(db : NotesDatabase) : NoteDao = db.noteDao

    @Singleton
    @Provides
    @Named("baseurl")
    fun getBaseUrl(): String   = RetrofitHelper.baseUrl

    @Singleton
    @Provides
    fun getRetrofit(@Named("baseurl") baseUrl : String) : Retrofit =  Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
        GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun getApiInstance(retrofit: Retrofit) : ApiInterface = retrofit.create(ApiInterface::class.java)
}