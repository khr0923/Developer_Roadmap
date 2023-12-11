package com.example.mp_pr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mp_pr.login.LogInActivity

class StartActivity : AppCompatActivity() {
    lateinit var startBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        startBtn = findViewById<Button>(R.id.start_btn)

        startBtn.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }

    }
}