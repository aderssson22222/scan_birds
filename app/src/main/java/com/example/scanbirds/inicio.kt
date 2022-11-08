package com.example.scanbirds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val btn: Button = findViewById(R.id.login)
        btn.setOnClickListener {
            val intent: Intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        val btnRegistro: Button = findViewById(R.id.registro)
        btnRegistro.setOnClickListener {
            val intent: Intent = Intent(this, registro::class.java)
            startActivity(intent)
        }
    }
}