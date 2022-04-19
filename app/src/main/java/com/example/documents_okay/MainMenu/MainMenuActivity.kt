package com.example.documents_okay.MainMenu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.documents_okay.MainMenu.ui.contacts.ContactsFragment
import com.example.documents_okay.MainMenu.ui.documents.DocumentsFragment
import com.example.documents_okay.MainMenu.ui.profile.ProfileFragment
import com.example.documents_okay.R
import com.example.documents_okay.authorization.AuthorizationActivity
import com.google.android.material.navigation.NavigationView


class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var drawerLayout : DrawerLayout
    private val rotateOpen : Animation by lazy {AnimationUtils.loadAnimation(this, R.anim.rotate_open_animation)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_animatioin) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_animation) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_animation) }

    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        nav_drawer()
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)

        FAB()

    }

    private fun FAB(): Void? {

        val add_btn : View = findViewById(R.id.btn_add)
        val download_btn : View = findViewById(R.id.btn_download)
        val camera_btn : View = findViewById(R.id.btn_camera)

        fun setClickable(clicked: Boolean) {
            if(!clicked) {
                camera_btn.isClickable = false
                download_btn.isClickable = false
            } else {
                camera_btn.isClickable = true
                download_btn.isClickable = true
            }
        }

        fun setVisiblity(clicked : Boolean) {
            if(!clicked) {
                camera_btn.visibility = View.VISIBLE
                download_btn.visibility = View.VISIBLE
            } else {
                camera_btn.visibility = View.INVISIBLE
                download_btn.visibility = View.INVISIBLE
            }
        }

        fun setAnimation(clicked : Boolean) {
            if(!clicked) {
                camera_btn.startAnimation(fromBottom)
                download_btn.startAnimation(fromBottom)
                add_btn.startAnimation(rotateOpen)
            } else {
                camera_btn.startAnimation(toBottom)
                download_btn.startAnimation(toBottom)
                add_btn.startAnimation(rotateClose)
            }
        }

        fun onAddButtonClicked() {
            setVisiblity(clicked)
            setAnimation(clicked)
            setClickable(clicked)
            clicked = !clicked
        }

        add_btn.setOnClickListener {
            onAddButtonClicked()
        }
        camera_btn.setOnClickListener {
            Toast.makeText(this, "scanner", Toast.LENGTH_LONG).show()
        }

        download_btn.setOnClickListener {
            Toast.makeText(this, "Download", Toast.LENGTH_LONG).show()

        }
        return null
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

        val navigationHeader : View = navigationView.getHeaderView(0);
        navigationHeader.setOnClickListener {
            changeFragment(ProfileFragment())
        }

        return null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_top_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_avatar ->
                changeFragment(ProfileFragment())
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        when(item.itemId) {


            R.id.nav_documents ->
                changeFragment(DocumentsFragment())

            R.id.nav_contacts ->
                changeFragment(ContactsFragment())

            R.id.nav_logOut -> {
                val intent = Intent(this, AuthorizationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()




            }

        }

        return true

    }

    fun changeFragment(frag: Fragment) {
        drawerLayout.closeDrawer(GravityCompat.START)
        val fragment  = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.nav_host_fragment_content_main, frag).commit()
    }


}