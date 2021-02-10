package com.example.schedulemaster.bean

data class DateBean(var date: Int, var isThisMonth: Boolean) {

    override fun toString(): String {
        return """
            the date is $date and is this month $isThisMonth
        """.trimIndent()
    }
}