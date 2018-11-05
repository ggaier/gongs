package com.github.ggaier.gongs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Created by wenbo, 2018/10/19
 */
suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100  // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        val jobs = List(n) {
            launch {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Completed ${n * k} actions in $time ms")
}

val counterContext = newSingleThreadContext("counterContext")
var counter = 0

fun main(args: Array<String>) = runBlocking<Unit> {
    //sampleStart
    CoroutineScope(counterContext).massiveRun {
        counter++
    }
    println("Counter = ${counter}")
    //sampleEnd
}