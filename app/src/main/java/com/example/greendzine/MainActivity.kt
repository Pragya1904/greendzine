package com.example.greendzine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginButton=findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener{
            val email=findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
            val password=findViewById<EditText>(R.id.editTextTextPassword).text.toString()
            if (validateCredentials(email, password)) {
                val intent= Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        // Add your validation logic here
        return email == "admin@greendzine.com" && password == "admin"
    }
}