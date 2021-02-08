package com.example.test_just_for_test.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.test_just_for_test.R
import kotlinx.android.synthetic.main.activity_main.*

class ProxyTestActivity: Activity() {

    private val TAG: String = "TestJustForTest_ProxyTestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        test_button2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {

                var intent: Intent = Intent(this@ProxyTestActivity, StartForResultActivity::class.java)
                startActivityForResult(intent, 1)
            }

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i(TAG, "onActivityResult: get the result and the intent text is ${data?.getStringExtra("test")}")
    }
}