package com.rootboost

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.io.BufferedReader
import java.io.InputStreamReader

class BoostReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Thread {
            val script = """
                for pkg in $(pm list packages -3 | cut -f 2 -d ":"); do
                    if [ "$pkg" != "com.google.android.inputmethod.latin" ]; then
                        am force-stop "$pkg"
                    fi
                done
                echo 3 > /proc/sys/vm/drop_caches
                am kill-all
            """.trimIndent()

            try {
                val process = Runtime.getRuntime().exec(arrayOf("su", "-c", script))
                val output = BufferedReader(InputStreamReader(process.inputStream)).readText()
                val error = BufferedReader(InputStreamReader(process.errorStream)).readText()
                val exit = process.waitFor()

                BoostWidgetProvider.updateAll(context, if (exit == 0) "Done" else "Failed")
            } catch (e: Exception) {
                BoostWidgetProvider.updateAll(context, "Root error")
            }
        }.start()
    }
}
