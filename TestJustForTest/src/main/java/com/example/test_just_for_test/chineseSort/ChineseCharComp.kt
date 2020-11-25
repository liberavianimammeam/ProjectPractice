package com.example.test_just_for_test.chineseSort

import com.example.test_just_for_test.bean.Students
import java.text.Collator
import java.util.*
import kotlin.Comparator

@SuppressWarnings("unchecked")
class ChineseCharComp : Comparator<com.example.test_just_for_test.bean.Students>{

    val mCollator: Collator by lazy {
        Collator.getInstance()
    }

    override fun compare(o1: Students, o2: Students): Int {

        if (mCollator.compare(o1.name, o2.name) < 0)return -1
        else if (mCollator.compare(o1.name, o2.name) > 0) return 1
        else return 0;
    }
}