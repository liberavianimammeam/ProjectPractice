package com.example.spiderpicture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.ImageRequest
import com.example.spiderpicture.model.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewmodel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }
}