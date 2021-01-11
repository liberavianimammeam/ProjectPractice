package com.example.test_just_for_test.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintSet
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.test_just_for_test.R
import com.example.test_just_for_test.ui.fragment.SettingFragment2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback, View.OnClickListener {

    private val TAG = "TestJustForTest_MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val job = GlobalScope.launch(Dispatchers.Main){
        }
        Log.i(TAG, "onCreate: the class name is " + (Dispatchers.Main + job).javaClass.name  + "\n and the main class is " + Dispatchers.Main.javaClass.name)

        val dataStore: DataStore<Preferences> = this.createDataStore(name = "test")

        Log.i(TAG, "onCreate: the absolute path is " + this.filesDir.absolutePath)
        
//        test_placeHolder.setContentId(R.id.test_button2

//        supportFragmentManager.beginTransaction().replace(activity_main_fragment_1.id, SettingFragment()).commit()

//        val exampleCounterFlow: Flow<Int> = dataStore.data.map {
//            it[] ?: 0
//        }
        test_button1.setOnClickListener(this)
        test_button2.setOnClickListener(this)
        test_button3.setOnClickListener(this)
        test_placeHolder.setOnClickListener(this)

//        dataStore.edit {
//            val currentCounterValue = it["test"] ?: 0
//        }


//        var student: Students = Students("张三", 12, 15)
//        var student1: Students = Students("李四", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
//        var student2: Students = Students("王五", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
//        var student3: Students = Students("周吴", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
//        var student4: Students = Students("路人甲", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
//        var student5: Students = Students("炮灰乙", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
//        var student6: Students = Students("吃瓜丙", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
//        var student7: Students = Students("看戏丁", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
//        var student8: Students = Students("戊", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
//        var student9: Students = Students("戌", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
//
//        var cmp = ChineseCharComp()
////        var list: ArrayList<String> = arrayListOf("江西","南昌","上饶","上海","北京","呼伦贝尔","塞罕坝","九江","饥荒","只狼","黑魂","暗月之潮","敌之尖刀")
//        var list = arrayListOf<Students>(student, student1, student2, student3, student4, student5, student6, student7, student8, student9)
//        Collections.sort(list, cmp)
//        Log.i("Test_Just_For_Test_MainActivity", "onCreate: get 拼音 of 路人甲    " + PinyinComparator().getPinYin("路人甲女"))
////        Log.i("Test_Just_For_Test_MainActivity", "onCreate: list is $list")
////        var iter = list.iterator()
////        while (iter.hasNext()){
////            Log.i("Test_Just_For_Test_MainActivity", "onCreate: 排序之后  +   " + iter.next())
////        }
    }


    suspend fun test(): String{
        Log.i(TAG, "test: ???????????")
        delay(5000)
        return "test return after 5000"
    }
    @Nullable
    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference?
    ): Boolean {
        Log.i(TAG, "onPreferenceStartFragment: the caller is " + caller?.javaClass + "   and the pref is " + pref?.key)
        supportFragmentManager.beginTransaction().replace(
            R.id.activity_main_fragment_1,
            SettingFragment2(), "test").addToBackStack(null).commit()
        return true
    }

    override fun onStart() {
        super.onStart()
        test_group.visibility = View.GONE
        var mTextView: TextView = TextView(this)
        mTextView.minHeight = 100
        mTextView.minWidth = 200
        mTextView.text = "tesakldgjaldgja;dglkad'gakja;ldskgakldsjalkdjaldshkahdadhahat"
        mTextView.setBackgroundColor(Color.parseColor("#00ffff"))
        mTextView.id = R.id.testText1
        Log.i(TAG, "onCreate: the text id is " + mTextView.id)
        constraint_test.addView(mTextView)
        var mSet = ConstraintSet()
//        mSet.constrainHeight(mTextView.id, ConstraintLayout.LayoutParams.MATCH_PARENT)
//        mSet.constrainWidth(mTextView.id, 50)
        mSet.connect(mTextView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        mSet.connect(mTextView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        mSet.connect(mTextView.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
        mSet.connect(mTextView.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)

        mSet.applyTo(constraint_test)

        for (i in 1..10){
            var testReturnBool: Boolean = testCouReturn()
        }


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test_button1 -> {
                Log.i(TAG, "onClick: the press button is button1")
            }
            R.id.test_button2 -> {
                Log.i(TAG, "onClick: the press button is button2")
            }
            R.id.test_button3 -> {
                Log.i(TAG, "onClick: the press button is button3")
            }
            R.id.test_placeHolder -> {
                Log.i(TAG, "onClick: the press place is placeHolder")
            }
        }
    }

    fun testCouReturn(): Boolean{

        GlobalScope.launch {
            Log.i(TAG, "testCouReturn: from the delay before")
            delay(200)
            Log.i(TAG, "testCouReturn: from the delay after")
        }
        return false
    }

}
