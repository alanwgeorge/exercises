package exercises.coroutinecontinuations

import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

lateinit var continuation: Continuation<Int>

fun main() = runBlocking<Unit> {
    launch(Unconfined) {
        val foo = foo()
        printWithInfo("foo() = $foo")
    }
    10.downTo(0).forEach {
        continuation.resume(it)
    }
}

suspend fun foo() = bar()

suspend fun bar(): Int {
    while (true) {
        val i = suspendCoroutine<Int> {
            continuation = it
            printWithInfo("inside suspendCoroutine...")
        }

        printWithInfo("i: $i")

        if (i == 0) {
            return 0
        }
    }
}

fun printWithInfo(message: Any?) {
    println("[${Thread.currentThread().name}] $message")
}