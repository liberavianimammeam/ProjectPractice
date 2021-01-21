package com.example.fliemanager.bean

class FileNameBean(var name: String, var isDirectory: Boolean, var type: String) {

    override fun toString(): String {
        return """
            name: $name
            isDirectory: $isDirectory
            type: $type
        """.trimIndent()
    }
}