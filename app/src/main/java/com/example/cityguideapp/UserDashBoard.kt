package com.example.cityguideapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_user_dash_board.*

class UserDashBoard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_user_dash_board)

        featuredRecycler()

        navigationDrawer()


    }

    private fun navigationDrawer() {
        navigation_view.bringToFront()
        navigation_view.setNavigationItemSelectedListener(this)
        navigation_view.setCheckedItem(R.id.nav_home)

        drawerLayout = drawer_layout
        menu_icon.setOnClickListener {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        animateNavigationDrawer()
    }

    private fun animateNavigationDrawer() {
        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // Scale the View based on current slide offset
                val diffScaledOffset: Float = (slideOffset * (1 - END_SCALE)).toFloat()
                val offSetScale: Float = 1 - diffScaledOffset
                content.scaleX = offSetScale
                content.scaleY = offSetScale

                // Translate the View, accounting for the scaled width
                val xOffSet = drawerView.width * slideOffset
                val xOffSetDiff = content.width * diffScaledOffset / 2
                val xTranslation = xOffSet - xOffSetDiff
                content.translationX = xTranslation


            }
        })
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun featuredRecycler() {
        featured_recycler.setHasFixedSize(true)
        featured_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val featuredLocations = ArrayList<FeaturedHelper>()

        featuredLocations.add(
            FeaturedHelper(
                R.drawable.ic_baseline_restaurant_menu_24,
                "McDonald's",
                "Dummy text dummy text dummy text dummy text"
            )
        )
        featuredLocations.add(
            FeaturedHelper(
                R.drawable.ic_baseline_restaurant_menu_24,
                "McDonald's",
                "Dummy text dummy text dummy text dummy text"
            )
        )
        featuredLocations.add(
            FeaturedHelper(
                R.drawable.ic_baseline_restaurant_menu_24,
                "McDonald's",
                "Dummy text dummy text dummy text dummy text"
            )
        )

        featured_recycler.adapter = Featured_Adapter(featuredLocations)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    companion object {
        private val END_SCALE = 0.7
    }
}