package com.example.myapplication.data.remote

import com.example.myapplication.data.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService{
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    // Puedes agregar más endpoints después
    @GET("/posts/1/comentarios")
    suspend fun getPostById(@Path("id") id: Int): Post

    @POST("/álbumes")
    suspend fun createPost(@Body post: Post): Post
}