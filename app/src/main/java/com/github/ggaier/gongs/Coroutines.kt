package com.github.ggaier.gongs

import kotlinx.coroutines.experimental.coroutineScope
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * Created by wenbo, 2018/10/19
 */
fun main(args: Array<String>) = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope { // Creates a new coroutine scope
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before nested launch
    }

    println("Coroutine scope is over") // This line is not printed until nested launch completes
}

//fun main(args: Array<String>) = runBlocking {
//    // this: CoroutineScope
//    launch {
//        // launch new coroutine in the scope of runBlocking
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//}