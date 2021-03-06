package io.petros.movies.presentation

import android.os.StrictMode
import io.petros.movies.BuildConfig

/* DISK */

private const val PREFIX_SPEK = "org.spekframework."
private const val PREFIX_J_UNIT = "org.junit."

fun permitDiskReads(block: () -> Any?): Any? {
    return when {
        isTest(PREFIX_SPEK) -> block()
        isTest(PREFIX_J_UNIT) -> block()
        BuildConfig.DEBUG -> {
            val currentThreadPolicy = StrictMode.getThreadPolicy()
            val tmpThreadPolicy = StrictMode.ThreadPolicy.Builder(currentThreadPolicy)
                .permitDiskReads().build()
            StrictMode.setThreadPolicy(tmpThreadPolicy)
            val value = block()
            StrictMode.setThreadPolicy(currentThreadPolicy)
            value
        }
        else -> block()
    }
}

fun isTest(prefix: String): Boolean {
    for (stackTrace in Thread.currentThread().stackTrace) {
        if (stackTrace.className.startsWith(prefix)) {
            return true
        }
    }
    return false
}
