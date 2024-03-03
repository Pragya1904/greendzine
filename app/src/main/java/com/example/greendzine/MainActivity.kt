package com.example.greendzine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginButton=findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener{
            val intent= Intent(this@MainActivity,HomeActivity::class.java)
            startActivity(intent)
    }
    }
}