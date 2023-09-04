package com.example.notesapplofcoding.repository

import com.example.notesapplofcoding.api.ApiInterface
import javax.inject.Inject

class PostRepository @Inject constructor (  val apiInstance : ApiInterface) {

    suspend fun getPost() = apiInstance.getPost()
}