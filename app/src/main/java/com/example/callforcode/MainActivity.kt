package com.example.callforcode

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.callforcode.Fragments.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_nav_bar.*

enum class ProviderType{
    BASIC,
    GOOGLE
}
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var homeFragment: HomeFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var  favoriteFragment: FavoriteFragment
    lateinit var adminFragment: AdminFragment
    lateinit var registerFragment: RegisterFragment



    override fun onStart() {
        super.onStart()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle: Bundle? = intent.extras
        val email:String? =  bundle?.getString("email")
        val provider:String? = bundle?.getString("provider")
        val displayName:String? = bundle?.getString("name")

        val prefs: SharedPreferences.Editor? = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs?.putString("email",email)
        prefs?.putString("provider",provider)
        prefs?.putString("name",displayName)
        prefs?.apply()

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

        setHeaderData()



        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


    }



    private fun setHeaderData() {
        val prefToHeader: SharedPreferences? =  getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val headerName = prefToHeader?.getString("name", "")
        val headerMail = prefToHeader?.getString("email","")

        val headView  = nav_view.getHeaderView(0)
        val usernameTextView= headView.findViewById<TextView>(R.id.header_username)
        val emailTextView = headView.findViewById<TextView>(R.id.header_email)

        usernameTextView.text = headerName
        emailTextView.text = headerMail
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
            R.id.reg_item ->{
                registerFragment = RegisterFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,registerFragment)
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