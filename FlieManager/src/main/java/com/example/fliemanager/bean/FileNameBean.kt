package com.example.fliemanager.bean

class FileNameBean(var name: String, var isDirectory: Boolean, var type: String, var path: String) {

    override fun toString(): String {
        return """
            name: $name
            isDirectory: $isDirectory
            type: $type
            path: $path
        """.trimIndent()
    }

    class FileNamePositionBean(var nameBean: FileNameBean, var position: Int)

}