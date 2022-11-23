package com.example.scanbirds

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.scanbirds.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    var pass:String?=""
    var correo:String?=""
    var email_bd:String?=""
    var pass_bd:String?=""
    var name_bd:String?=""
    var last_bd:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recuperar.setOnClickListener {
            val intent: Intent = Intent(this, recuperar::class.java)
            startActivity(intent)
        }


        binding.buttonLogin.setOnClickListener {

            correo = binding.Usuario.text.toString()
            pass = binding.Contrasena.text.toString()

            if (correo!!.isEmpty()){
                binding.Usuario.setHint("Ingrese su usuario")
                binding.Usuario.setHintTextColor(Color.RED)
            }
            if (pass!!.isEmpty()){
                binding.Contrasena.setHint("Ingrese su contraseña")
                binding.Contrasena.setHintTextColor(Color.RED)
            }

            val room = Room.databaseBuilder(this, bdUsuarios::class.java,"bd_scanbirds").build()


            if (correo!="" && pass!="") {
                lifecycleScope.launch {
                    var usuRes = room.daoUsuario().buscarUsuario(correo!!)

                    if (usuRes!=null) {
                        email_bd = usuRes!!.email
                        pass_bd = usuRes!!.contrasena
                        name_bd = usuRes!!.nombres
                        last_bd = usuRes!!.apellidos
                        validarRoom(email_bd!!, pass_bd!!, name_bd!!, last_bd!!, correo!!, pass!!)
                    }else{
                        noexiste()
                    }
                }
            }

        }

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
                Toast.makeText(this, "CONTRASEÑA INCORRECTA", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun validarRoom(email_bd:String,pass_bd:String,name_bd:String,last_bd:String,correo:String,pass:String){




            if(correo == email_bd && pass == pass_bd) {

                val intent = Intent(this, home::class.java)
                intent.putExtra("email",email_bd)
                intent.putExtra("nombres",name_bd)
                intent.putExtra("apellidos",last_bd)
                startActivity(intent)
            }else{
                Toast.makeText(this, "CREDENCIALES INCORRECTAS", Toast.LENGTH_LONG).show()
            }

    }

    fun noexiste(){
        Toast.makeText(this, "CORREO NO REGISTRADO EN LA BASE DE DATOS", Toast.LENGTH_LONG).show()
    }
}