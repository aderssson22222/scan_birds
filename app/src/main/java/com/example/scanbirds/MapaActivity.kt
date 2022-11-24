package com.example.scanbirds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var mapa: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        crearFragment()

    }

    fun crearFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(mapaCreado: GoogleMap) {
        mapa= mapaCreado
        crearMarker()
    }

    private fun crearMarker(){
        val punto= LatLng(5.175047, -72.545090)
        val marcador:MarkerOptions = MarkerOptions().position(punto).title("Chesca")
        mapa.addMarker(marcador)
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(punto,16f),8000,null)

    }
}