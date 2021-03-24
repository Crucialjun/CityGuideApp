package com.example.cityguideapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_retailer_dashboard.*

class RetailerDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retailer_dashboard)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_dashboard, RetailerDashboardFragment())
            .commit()

        bottomMenu()
    }

    private fun bottomMenu() {
        chip_navigation.setOnItemSelectedListener {
            var fragment: Fragment? = null
            when (it) {
                R.id.bottom_nav_dashbord -> {
                    fragment = RetailerDashboardFragment()
                }

                R.id.bottom_nav_manage -> {
                    fragment = ManageFragment()
                }

                R.id.bottom_nav_profile -> {
                    fragment = ProfileFragment()
                }
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_dashboard, fragment!!)
                .commit()
        }
    }
}