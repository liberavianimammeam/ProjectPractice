package com.example.spiderpicture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.ImageRequest
import com.example.spiderpicture.model.MainActivityViewModel
import com.example.spiderpicture.request.NormalRequest
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.util.ResolveUtil

class MainActivity : AppCompatActivity() {

    private val viewmodel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel.getData(Global.urlRoot.plus(Global.urlSecondLevel[0]), this)
        RequestUtil.text.observe(this, Observer {
            var textView = findViewById<TextView>(R.id.activity_main_test_text)
            textView.text = it
            ResolveUtil.resolveMnxz(it)
//            ResolveUtil.resolveRoot(it)
        })
        
    }
}