package com.example.test_just_for_test.chineseSort

import android.util.Log
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType


class PinyinComparator: Comparator<Any> {
    override fun compare(o1: Any?, o2: Any?): Int {
        return 0
    }

    fun getPinYin(input: String): String{
        val format = HanyuPinyinOutputFormat()
        format.caseType = HanyuPinyinCaseType.LOWERCASE
        format.toneType = HanyuPinyinToneType.WITH_TONE_NUMBER
        format.vCharType = HanyuPinyinVCharType.WITH_V

        val inputChar = input.toCharArray()
        var output: String = ""
        try {
            for (i in 0 until inputChar.size) {
                // \\u4E00是unicode编码，判断是不是中文
                if (Character.toString(input[i]).matches(
                        Regex("[\\u4E00-\\u9FA5]+")
                    )
                ) {
                    // 将汉语拼音的全拼存到temp数组
                    val temp = PinyinHelper.toHanyuPinyinStringArray(
                        inputChar[i], format
                    )
                    // 取拼音的第一个读音
                    output += temp[0]
                } else if (inputChar[i] > 'A' && inputChar[i] < 'Z') {
                    output += Character.toString(inputChar[i])
                    output = output.toLowerCase()
                }
//                output += Character.toString(input[i])
            }
        } catch (e: Exception) {
            Log.e("Exception", e.toString())
        }
        return output
    }
}