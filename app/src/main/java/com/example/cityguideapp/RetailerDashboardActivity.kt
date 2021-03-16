package com.example.cityguideapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_retailer_dashboard.*

class RetailerDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retailer_dashboard)

        val userDetails = SessionManager(this).getUserDetails()


        val fullname = userDetails[SessionManager(this).KEY_FULLNAME]
        val phoneNumber = userDetails[SessionManager(this).KEY_PHONENUMBER]

        val text = "$fullname \n $phoneNumber"
        textView.text = text
    }
}