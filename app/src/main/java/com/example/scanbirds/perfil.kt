package com.example.scanbirds

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class perfil : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.tolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.navegation_drawer_open,R.string.navegation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> perfil()
            R.id.item2 ->Toast.makeText(this, "Este es el perfil", Toast.LENGTH_SHORT).show()
            R.id.item3 ->explorar()
            R.id.item4 ->scan()
            R.id.item5 ->comunidad()
            R.id.item6 ->coleccion()
            R.id.item7 ->visita()
            R.id.item8 ->cerrar()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }



    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun perfil(){
        val intent: Intent = Intent(this, home::class.java)
        startActivity(intent)
    }
    fun explorar(){
        val intent: Intent = Intent(this, explorar::class.java)
        startActivity(intent)
    }
    fun scan(){
        val intent: Intent = Intent(this, scan::class.java)
        startActivity(intent)
    }
    fun comunidad(){
        val intent: Intent = Intent(this, comunidad::class.java)
        startActivity(intent)
    }
    fun coleccion(){
        val intent: Intent = Intent(this, coleccion::class.java)
        startActivity(intent)
    }
    fun visita(){
        val intent: Intent = Intent(this, visita::class.java)
        startActivity(intent)
    }
    fun cerrar(){
        val intent: Intent = Intent(this, inicio::class.java)
        startActivity(intent)
    }
}