package com.example.httpweather.util

import com.example.httpweather.bean.LocationBean
import java.text.Collator

class SortUtil{

    class LocationSortUtil: Comparator<LocationBean>{
        val mCollator: Collator by lazy {
            Collator.getInstance()
        }

        override fun compare(o1: LocationBean, o2: LocationBean): Int {
            if (mCollator.compare(o1.cityZh, o2.cityZh) < 0) return -1
            else if (mCollator.compare(o1.cityZh, o2.cityZh) > 0) return 1
            else return 0
        }
    }

}