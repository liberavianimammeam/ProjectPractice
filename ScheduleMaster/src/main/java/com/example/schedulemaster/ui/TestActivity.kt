package com.example.schedulemaster.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulemaster.R

class TestActivity: AppCompatActivity() {

    private val TAG = "ScheduleMaster_TestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var view = layoutInflater.inflate(R.layout.activity_test, null, false)
        setContentView(view)
        Log.i(TAG, "onCreate: the view height is ${view.height} and the view width is ${view.width}")
        view.viewTreeObserver.addOnGlobalLayoutListener {
            Log.i(TAG, "onCreate: the view height is ${view.height} and the view width is ${view.width}")
        }

        findViewById<Button>(R.id.at_button_to_schedule).setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@TestActivity, ScheduleActivity::class.java))
            }

        })

    }



}