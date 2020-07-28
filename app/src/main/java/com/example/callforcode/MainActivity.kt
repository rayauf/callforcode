package com.example.callforcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.callforcode.Fragments.AdminFragment
import com.example.callforcode.Fragments.FavoriteFragment
import com.example.callforcode.Fragments.HomeFragment
import com.example.callforcode.Fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_nav_bar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var homeFragment: HomeFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var  favoriteFragment: FavoriteFragment
    lateinit var adminFragment: AdminFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = ""

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawLayout,
            toolbar,
            (R.string.open),
            (R.string.close)){

        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav_view.setCheckedItem(R.id.home_item)
        nav_view.setNavigationItemSelectedListener(this)

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.home_item -> {
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.profile_item ->{
                profileFragment = ProfileFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,profileFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("null")
                    .commit()
            }

            R.id.fav_item ->{
                favoriteFragment = FavoriteFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,favoriteFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("null")
                    .commit()
            }

            R.id.adm_item ->{
                adminFragment = AdminFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,adminFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("null")
                    .commit()
            }
        }
        drawLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawLayout.isDrawerOpen(GravityCompat.START)){
          drawLayout.closeDrawer(GravityCompat.START)
       }
        else{
            super.onBackPressed()
        }

    }
}