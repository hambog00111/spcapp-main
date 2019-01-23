package com.example.mis_pc_11.spc_app1

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentHome()).commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentHome()).commit()
            }
            R.id.nav_thecity -> {
                supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentTheCity()).commit()
            }
            R.id.nav_goverment ->{
                supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentGoverment()).commit()
            }
            R.id.nav_economy ->{
                supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentEconomy()).commit()
            }
            R.id.nav_sanpablo ->{
                supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentSanPablo()).commit()
            }
            R.id.nav_tourism->{
                supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentTourism()).commit()
            }

            R.id.nav_gallery->{
                supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentGallery()).commit()
            }
            R.id.nav_bplo->{
                supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentFillUp()).commit()
            }
            else -> {
                supportFragmentManager.beginTransaction().replace(R.id.frag_container, FragmentHome()).commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

