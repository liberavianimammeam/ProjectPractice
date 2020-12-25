package com.example.testjsoup.bean

data class IDCheckBean(var resCode: Int,
                       var errInfo: String,
                       var extraInfo: ExtraInfo,
                       var extraMethod: String) {

    data class ExtraInfo(var createTime: String,
                         var faceID: String,
                         var faceImage: String,
                         var fingerID: String,
                         var hasPassedNum: String,
                         var validData: String,
                         var validStartDate: String,
                         var voiceOn: String,
                         var result: String,
                         var extraInfo: String,
                         var errorDetail: String,
                         var qrcode: String,
                         var ticketOrMemberType: String,
                         var voice: String,
                         var checkNum: String,
                         var iccard: String,
                         var idcard: String,
                         var hasCheckedNum: String,
                         var isAuthCard: String,
                         var isManager: String,
                         var passCount: String,
                         var name: String,
                         var now: String
                         )


}