package com.example.cityguideapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)




        vp_on_boarding.apply {
            adapter = SliderAdapter(context)
        }

        addDots()


    }

    fun addDots(){
        val dots = arrayOfNulls<TextView>(4,)

        for (i in dots.indices){
            dots[i] = TextView(this)
            dots[i]?.text = Html.fromHtml("&#8226;")
            dots[i]?.textSize= 35F

            dots_layout.addView(dots[i])
        }


    }
}