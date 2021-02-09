package com.example.schedulemaster.bean

import androidx.room.PrimaryKey

data class DiaryBean(
    @PrimaryKey
    val id: Int,
    var diaryTitle: String,
    var diary: String,
    var writeTime: Float,
    var remindTime: Float
)