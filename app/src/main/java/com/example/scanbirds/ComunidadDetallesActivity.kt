package com.example.scanbirds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.scanbirds.databinding.ActivityComunidadDetallesBinding
import com.example.scanbirds.databinding.ActivityRecuperarBinding

class ComunidadDetallesActivity : AppCompatActivity() {
    lateinit var binding: ActivityComunidadDetallesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityComunidadDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var mapa = Intent(this, MapaActivity::class.java)

        var Nombres = intent.getStringExtra("Nombres")
        var Apellidos = intent.getStringExtra("Apellidos")
        var Aves = intent.getStringExtra("Aves")
        var Rango = intent.getStringExtra("Rango")
        var Avatar = intent.getStringExtra("Avatar")

        binding.NombresDetalles.text=Nombres
        binding.ApellidosDetalles.text=Apellidos
        binding.AvezDetalles.text=Aves
        binding.RangoDetalles.text=Rango


        Glide.with(this)
            .load(Avatar)
            .into(binding.ImagenDetalles);
        binding.BtnMapaDetalles.setOnClickListener {
            startActivity(mapa)
        }
    }
}