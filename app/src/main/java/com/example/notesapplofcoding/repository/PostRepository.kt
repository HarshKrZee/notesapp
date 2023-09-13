package com.example.notesapplofcoding.repository

import com.example.notesapplofcoding.api.ApiInterface

class PostRepository (private val apiInstance : ApiInterface) {

    suspend fun getPost() = apiInstance.getPost()
}