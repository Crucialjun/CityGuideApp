package com.example.cityguideapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMER : Long = 5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        val sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)

        background_image.animation = sideAnim
        powered_by_line.animation = bottomAnim

        val sharedPreferences: SharedPreferences = getSharedPreferences("onBoarding", MODE_PRIVATE)

        val isFirstTime = sharedPreferences.getBoolean("firstTime", true)

        Handler().postDelayed({
            if (isFirstTime) {
                sharedPreferences.edit().putBoolean("firstTime", false).apply()
                val intent = Intent(applicationContext, OnboardingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(applicationContext, UserDashBoard::class.java)
                startActivity(intent)
                finish()
            }

        }, SPLASH_TIMER)
    }
}