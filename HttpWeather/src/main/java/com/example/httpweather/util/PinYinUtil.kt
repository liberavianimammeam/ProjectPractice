package com.example.httpweather.util

import android.util.Log
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
import java.util.*
import kotlin.Comparator

class PinYinUtil{
    companion object{
        private val TAG: String = "HttpWeather_PinYinUtil"
        //获取传入数据的汉语拼音
        fun getPinYin(input: String): String{
            val formate = HanyuPinyinOutputFormat()
            formate.caseType = HanyuPinyinCaseType.LOWERCASE
            formate.toneType = HanyuPinyinToneType.WITHOUT_TONE
            formate.vCharType = HanyuPinyinVCharType.WITH_V

            val inputChar = input.toCharArray()
            var output: String = ""
            try {
                for (i in inputChar.indices){
                    if (Character.toString(input[i]).matches(
                            Regex("[\\u4E00-\\u9FA5]+")
                        )
                    ){
                        val temp = PinyinHelper.toHanyuPinyinStringArray(inputChar[i], formate)
                        output += temp[i]
                    }else if (inputChar.get(i) in 'A'..'Z'){
                        output += Character.toString(inputChar.get(i))
                        output = output.toLowerCase()
                    }
                }
            }catch (e: Exception){
                Log.i(TAG, "getPinYin: went wrong")
            }
            return output
        }
    }

}