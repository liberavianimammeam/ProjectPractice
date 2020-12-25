package com.example.testjsoup.bean

data class HeartBeatResultBean(var resultCode: Int,
                               var errInfo: String,
                               var extraInfo: IDCheckBean.ExtraInfo,
                               var extraMethod: String){
}