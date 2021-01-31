package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.wajahatkarim3.zocdoc.R
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(delayInMillis = 2000) {
            var isDoctor = Random.nextBoolean()
            if (isDoctor) {
                var intent = Intent(this, DoctorMainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}