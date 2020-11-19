package com.example.test_just_for_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test_just_for_test.bean.Students
import com.example.test_just_for_test.chineseSort.ChineseCharComp
import com.example.test_just_for_test.chineseSort.PinyinComparator
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var student: Students = Students("张三", 12, 15)
        var student1: Students = Students("李四", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
        var student2: Students = Students("王五", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
        var student3: Students = Students("周吴", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
        var student4: Students = Students("路人甲", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
        var student5: Students = Students("炮灰乙", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
        var student6: Students = Students("吃瓜丙", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
        var student7: Students = Students("看戏丁", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
        var student8: Students = Students("戊", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())
        var student9: Students = Students("戌", (Math.random()*10 + 1).toInt(), (Math.random()*10 + 1).toInt())

        var cmp = ChineseCharComp<String>()
//        var list: ArrayList<String> = arrayListOf("江西","南昌","上饶","上海","北京","呼伦贝尔","塞罕坝","九江","饥荒","只狼","黑魂","暗月之潮","敌之尖刀")
        var list = arrayListOf<Students>(student, student1, student2, student3, student4, student5, student6, student7, student8, student9)
        Collections.sort(list, cmp)
        Log.i("Test_Just_For_Test_MainActivity", "onCreate: get 拼音 of 路人甲    " + PinyinComparator().getPinYin("路人甲女"))
//        Log.i("Test_Just_For_Test_MainActivity", "onCreate: list is $list")
//        var iter = list.iterator()
//        while (iter.hasNext()){
//            Log.i("Test_Just_For_Test_MainActivity", "onCreate: 排序之后  +   " + iter.next())
//        }
    }
}
