package com.example.notesapplofcoding.api

import com.example.notesapplofcoding.model.Post
import retrofit2.http.GET

interface  ApiInterface  {

    @GET("posts")
    suspend fun getPost() : List<Post>
}