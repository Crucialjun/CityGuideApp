package com.example.cityguideapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMER : Long = 5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        val sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim)

        background_image.animation = sideAnim
        powered_by_line.animation = bottomAnim

        Handler().postDelayed(object : Runnable{
            override fun run() {
                val intent = Intent(applicationContext,UserDashBoard::class.java)
                startActivity(intent)
                finish()
            }

        },SPLASH_TIMER)
    }
}