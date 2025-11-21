package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.Screen.PostScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApplicationTheme {
                val postViewModel: com.example.myapplication.viewmodel.PostViewModel = viewModel()

                PostScreen(viewModel = postViewModel)
            }
        }

    }}