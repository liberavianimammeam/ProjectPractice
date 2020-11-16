package com.example.httpweather.util

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f: () -> Unit){
    IO_EXECUTOR.execute(f)
}