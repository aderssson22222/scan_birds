package com.example.scanbirds

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

        if (correo.isEmpty()){
            binding.Usuario.setHint("Ingrese su usuario")
            binding.Usuario.setHintTextColor(Color.RED)
        }
        if (pass.isEmpty()){
            binding.Contrasena.setHint("Ingrese su contraseña")
            binding.Contrasena.setHintTextColor(Color.RED)
        }
        if (correo!="" && pass!=""){
            if(correo=="andersson@gmail.com"&& pass=="andersson") {
                Toast.makeText(this, "Bienvenido a SCAN BIRDS", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "CREDENCIALES INCORRECTAS", Toast.LENGTH_LONG).show()
            }
        }
    }
}