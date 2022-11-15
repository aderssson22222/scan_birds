package com.example.scanbirds

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.scanbirds.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recuperar.setOnClickListener {
            val intent: Intent = Intent(this, recuperar::class.java)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener { validar() }

    }
    fun validar(){
        val correo:String= binding.Usuario.text.toString()
        val pass:String= binding.Contrasena.text.toString()

        val pref=getSharedPreferences(correo,Context.MODE_PRIVATE)
        val email_sb=pref.getString("email","")
        val pass_sb=pref.getString("contraseña","")
        val name_sb=pref.getString("nombres","")
        val last_sb=pref.getString("apellidos","")

        if (correo.isEmpty()){
            binding.Usuario.setHint("Ingrese su usuario")
            binding.Usuario.setHintTextColor(Color.RED)
        }
        if (pass.isEmpty()){
            binding.Contrasena.setHint("Ingrese su contraseña")
            binding.Contrasena.setHintTextColor(Color.RED)
        }
        if (correo!="" && pass!=""){
            if(correo==email_sb && pass== pass_sb) {
                //Toast.makeText(this, "Bienvenido a SCAN BIRDS $name_sb $last_sb", Toast.LENGTH_LONG).show()
                val intent = Intent(this, home::class.java)
                intent.putExtra("email",email_sb)
                intent.putExtra("nombres",name_sb)
                intent.putExtra("apellidos",last_sb)
                startActivity(intent)
            }else{
                Toast.makeText(this, "CREDENCIALES INCORRECTAS", Toast.LENGTH_LONG).show()
            }
        }
    }
}