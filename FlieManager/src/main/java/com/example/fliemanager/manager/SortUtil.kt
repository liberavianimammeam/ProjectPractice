package com.example.fliemanager.manager

import android.util.Log
import com.example.fliemanager.bean.FileNameBean
import java.text.Collator
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

object SortUtil {

    private val TAG: String = "FileManager_SortUtil"

    class SortCharComp: Comparator<FileNameBean>{

        val mCollator: Collator by lazy {
            Collator.getInstance()
        }

        override fun compare(o1: FileNameBean, o2: FileNameBean): Int {
            if (mCollator.compare(o1.name, o2.name) < 0 ) return -1
            else if (mCollator.compare(o1.name, o2.name) > 0) return 1
            else return 0
        }

    }

    fun sortFileList(list: ArrayList<FileNameBean>): ArrayList<FileNameBean>{
        var cmp = SortCharComp()
        Collections.sort(list, cmp)
        Log.i(TAG, "sortFileList: the list after sort is $list")
        return list
    }

}