package com.example.documents_okay.MainMenu

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.documents_okay.R
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        nav_drawer()


    }

    private fun nav_drawer() : Void? {
        val toolbar : com.google.android.material.appbar.MaterialToolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout : DrawerLayout= findViewById(R.id.drawer_layout)
        val navigationView : NavigationView= findViewById(R.id.navigation_view)

        val actionBarDrawerToggle : ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openNavDrawer,
            R.string.closeNavDrawer)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        return null
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

}