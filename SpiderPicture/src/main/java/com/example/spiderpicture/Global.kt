package com.example.spiderpicture

object Global {

    object Pages{
        val mnxz: String = "mnxz"
        val jdly: String = "jdly"
        val flj: String = "flj"
        val tags: String = "tags"
        val TAG: String = "GLOBAL_TAGS"
    }

    val urlRoot: String = "https://www.gteman.com"
    var urlSecondLevel: ArrayList<String> = arrayListOf(Pages.mnxz, Pages.jdly, Pages.flj, Pages.tags)

    val intentTagForThirdLevelDetail: String = "THIRD_LEVEL_INTENT"

}