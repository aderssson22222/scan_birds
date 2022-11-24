package com.example.scanbirds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.scanbirds.databinding.ActivityRecuperarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class recuperar : AppCompatActivity() {
    lateinit var binding: ActivityRecuperarBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecuperarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth

        binding.btnRecuperar.setOnClickListener {
            var correo = binding.UsuarioRecuperar.text.toString()
            recuperar(correo)
        }
    }
    private fun recuperar(correo:String){
        firebaseAuth.sendPasswordResetEmail(correo).addOnCompleteListener(){
            task->
            if (task.isSuccessful){
                Toast.makeText(this, "Favor revise su email para realizar el cambio de contraseña", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, login::class.java))
            }else{
                Toast.makeText(this, "ingrese un correo", Toast.LENGTH_LONG).show()
            }
        }
    }
}