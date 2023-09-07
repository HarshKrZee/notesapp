package com.example.notesapplofcoding.repository

import com.example.notesapplofcoding.api.ApiInterface

class PostRepository (  val apiInstance : ApiInterface) {

    suspend fun getPost() = apiInstance.getPost()
}