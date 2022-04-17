package com.example.documents_okay.MainMenu

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.documents_okay.MainActivity
import com.example.documents_okay.MainMenu.ui.contacts.ContactsFragment
import com.example.documents_okay.MainMenu.ui.documents.DocumentsFragment
import com.example.documents_okay.MainMenu.ui.profile.ProfileFragment
import com.example.documents_okay.R
import com.google.android.material.navigation.NavigationView


class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)


        nav_drawer()
    }

    private fun nav_drawer(): Void? {
        val toolbar: com.google.android.material.appbar.MaterialToolbar =
            findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        val actionBarDrawerToggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openNavDrawer,
            R.string.closeNavDrawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        return null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.nav_documents ->
                changeFragment(DocumentsFragment())

            R.id.nav_contacts ->
                changeFragment(ContactsFragment())

        }

        return true

    }

    fun changeFragment(frag: Fragment) {
        drawerLayout.closeDrawer(GravityCompat.START)
        val fragment  = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.nav_host_fragment_content_main, frag).commit()
    }

}