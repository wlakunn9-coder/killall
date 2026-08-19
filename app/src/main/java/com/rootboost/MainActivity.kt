package com.rootboost

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = findViewById<TextView>(R.id.status)
        val log = findViewById<TextView>(R.id.log)
        val button = findViewById<Button>(R.id.boostButton)

        button.setOnClickListener {
            status.text = "Running..."
            log.text = "Requesting root..."
            sendBroadcast(Intent(this, BoostReceiver::class.java))
        }
    }
}
