package com.example.test_just_for_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import android.view.KeyEvent
import androidx.annotation.Nullable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.test_just_for_test.bean.Students
import com.example.test_just_for_test.chineseSort.ChineseCharComp
import com.example.test_just_for_test.chineseSort.PinyinComparator
import com.example.test_just_for_test.ui.SettingFragment
import com.example.test_just_for_test.ui.SettingFragment2
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.android.HandlerDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import kotlin.collections.ArrayList
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private val TAG = "TestJustForTest_MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val job = GlobalScope.launch(Dispatchers.Main){
        }
        Log.i(TAG, "onCreate: the class name is " + (Dispatchers.Main + job).javaClass.name  + "\n and the main class is " + Dispatchers.Main.javaClass.name)

        val dataStore: DataStore<Preferences> = this.createDataStore(name = "test")

        Log.i(TAG, "onCreate: the absolute path is " + this.filesDir.absolutePath)

//        supportFragmentManager.beginTransaction().replace(activity_main_fragment_1.id, SettingFragment()).commit()

//        val exampleCounterFlow: Flow<Int> = dataStore.data.map {
//            it[] ?: 0
//        }


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
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_fragment_1,SettingFragment2(), "test").addToBackStack(null).commit()
        return true
    }

}
