package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Post
import com.example.myapplication.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private val repository = PostRepository()
    private val _postslist = MutableStateFlow<List<Post>>(emptyList())

    val postList: StateFlow<List<Post>> = _postslist

    private val _selectedPost = MutableStateFlow<Post?>(null)
    val selectedPost: StateFlow<Post?> = _selectedPost

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    fun getPostById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _selectedPost.value = repository.getPostById(id)
            } catch (e: Exception) {
                println("Error al obtener post: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun createPost(title: String, body: String, userId: Int = 1) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newPost = Post(
                    userId = userId,
                    id = 0, // La API asigna el ID
                    title = title,
                    body = body
                )
                val createdPost = repository.createPost(newPost)

                fetchPosts()
            } catch (e: Exception) {
                println("Error al crear post: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                _postslist.value = repository.getPost()
            } catch (e: Exception) {
                print("error al obtener los datos: ${e.localizedMessage}")
            }

        }
    }


}