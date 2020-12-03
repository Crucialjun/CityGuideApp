package com.example.cityguideapp

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_user_dash_board.*

class UserDashBoard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


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

        val drawerLayout = drawer_layout
        menu_icon.setOnClickListener {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
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
}