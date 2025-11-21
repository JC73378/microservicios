package com.example.myapplication.repository

import com.example.myapplication.data.model.Post
import com.example.myapplication.data.remote.RetrofitInstance

class PostRepository {

    suspend fun getPost(): List<Post>{
        return RetrofitInstance.api.getPosts()
    }

    // Agregar estas nuevas funciones
    suspend fun getPostById(id: Int): Post {
        return RetrofitInstance.api.getPostById(id)
    }

    suspend fun createPost(post: Post): Post {
        return RetrofitInstance.api.createPost(post)
    }
}