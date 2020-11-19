package com.example.test_just_for_test.bean

data class Students(var name:String, var id: Int, var age: Int){
    override fun toString(): String {
        return "name is ".plus(name).plus(" id is ").plus(id).plus(" age is ").plus(age)
    }
}