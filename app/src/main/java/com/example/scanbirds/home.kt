package com.example.scanbirds

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.DialogTitle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class home : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        firebaseAuth = Firebase.auth
        /*
        val email = intent.getStringExtra("email")
        val nombres = intent.getStringExtra("nombres")
        val apellidos = intent.getStringExtra("apellidos")

        Toast.makeText(this, "Bienvenid@ a SCAN BIRDS $nombres $apellidos", Toast.LENGTH_LONG)
            .show()
        val scan_F = ScanFragment()
        val bundle = Bundle()
        bundle.putString("email", email)
        scan_F.arguments = bundle
         */

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.tolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navegation_drawer_open,
            R.string.navegation_drawer_close
        )
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.item1 -> replaceFragment(InicioFragment(), it.title.toString())
                R.id.item2 -> replaceFragment(PerfilFragment(), it.title.toString())
                R.id.item3 -> replaceFragment(ExplorarFragment(), it.title.toString())
                R.id.item4 -> replaceFragment(ScanFragment(), it.title.toString())
                R.id.item5 -> replaceFragment(ComunidadFragment(), it.title.toString())
                R.id.item6 -> replaceFragment(ColeccionFragment(), it.title.toString())
                R.id.item7 -> replaceFragment(VisitaFragment(), it.title.toString())
                R.id.item8 -> cerrar()
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        drawer.closeDrawers()
        setTitle(title)
    }


    fun cerrar() {
        firebaseAuth.signOut()
        val intent = Intent(this, inicio::class.java)
        startActivity(intent)
    }
}