package com.example.scanbirds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            val intent = Intent(this, inicio::class.java)
            startActivity(intent)
            finish()
        },4050)
    }
}