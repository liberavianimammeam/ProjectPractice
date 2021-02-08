package com.example.test_just_for_test.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_just_for_test.R

class StartForResultActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var intent: Intent = Intent()
        intent.putExtra("test", "test")
        setResult(3, intent)
        finish()
    }
}