package com.example.scanbirds

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.scanbirds.databinding.ActivityLoginBinding
import com.example.scanbirds.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class registro : AppCompatActivity() {

    lateinit var binding: ActivityRegistroBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firestoreBd: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        firestoreBd = FirebaseFirestore.getInstance()
        binding.btnRegistrarse.setOnClickListener {
            //saveUser()
            registrarFirebase()
        }

    }

    fun saveUser(){

        val nombres:String = binding.Nombres.text.toString()
        val apellidos:String = binding.Apellidos.text.toString()
        val direccion:String = binding.Direccion.text.toString()
        val telefono:String = binding.Phone.text.toString()
        val correo:String = binding.Email.text.toString()
        val cotrasena:String = binding.sPassword.text.toString()
        val ccotrasena:String = binding.cPassword.text.toString()
        if (nombres.isEmpty()){
            binding.Nombres.setHint("Escriba sus nombres")
            binding.Nombres.setHintTextColor(Color.RED)
        }
        if (apellidos.isEmpty()){
            binding.Apellidos.setHint("Escriba sus apellidos")
            binding.Apellidos.setHintTextColor(Color.RED)
        }
        if (direccion.isEmpty()){
            binding.Direccion.setHint("Escriba su direccion")
            binding.Direccion.setHintTextColor(Color.RED)
        }
        if (telefono.isEmpty()){
            binding.Phone.setHint("Escriba su telefono")
            binding.Phone.setHintTextColor(Color.RED)
        }
        if (correo.isEmpty()){
            binding.Email.setHint("Escriba su correo")
            binding.Email.setHintTextColor(Color.RED)
        }
        if (cotrasena.isEmpty()){
            binding.sPassword.setHint("Escriba una contraseña")
            binding.sPassword.setHintTextColor(Color.RED)
        }
        if (ccotrasena.isEmpty()){
            binding.cPassword.setHint("Confirme su contraseña")
            binding.cPassword.setHintTextColor(Color.RED)
        }
        if(nombres!=""&&apellidos!=""&&direccion!=""&&telefono!=""&&correo!=""&&cotrasena!=""&&ccotrasena!="") {
            if (cotrasena != ccotrasena) {
                Toast.makeText(this, "La contraseñas no coinciden", Toast.LENGTH_LONG).show()
            } else {


                val room =
                    Room.databaseBuilder(this, bdUsuarios::class.java, "bd_scanbirds").build()

                var usuario = Usuario_Entidad(correo, nombres, apellidos, telefono, direccion, cotrasena)
                lifecycleScope.launch {
                room.daoUsuario().agregarusuario(usuario)
                var lista = room.daoUsuario().obtenerUsuarios()
                    for (usu in lista){
                        println("----------->>>>>${usu.email} -- ${usu.nombres}--${usu.apellidos}")
                    }
                }

                /* Guardar en local
                var pref = getSharedPreferences(correo, Context.MODE_PRIVATE)
                var editar = pref.edit()
                editar.putString("email", correo)
                editar.putString("nombres", nombres)
                editar.putString("apellidos", apellidos)
                editar.putString("direccion", direccion)
                editar.putString("telefono", telefono)
                editar.putString("contraseña", cotrasena)

                editar.commit()*/

                Toast.makeText(this, "Usuario Registrado Exitosamente", Toast.LENGTH_LONG).show()
                val intent: Intent = Intent(this, login::class.java)
                startActivity(intent)
            }
        }
    }

    fun registrarFirebase(){
        val correo:String = binding.Email.text.toString()
        val cotrasena:String = binding.sPassword.text.toString()
        val ccotrasena:String = binding.cPassword.text.toString()
        val nombres:String = binding.Nombres.text.toString()
        val apellidos:String = binding.Apellidos.text.toString()
        val direccion:String = binding.Direccion.text.toString()
        val telefono:String = binding.Phone.text.toString()
        var id:String

        if (nombres.isEmpty()){
            binding.Nombres.setHint("Escriba sus nombres")
            binding.Nombres.setHintTextColor(Color.RED)
        }
        if (apellidos.isEmpty()){
            binding.Apellidos.setHint("Escriba sus apellidos")
            binding.Apellidos.setHintTextColor(Color.RED)
        }
        if (direccion.isEmpty()){
            binding.Direccion.setHint("Escriba su direccion")
            binding.Direccion.setHintTextColor(Color.RED)
        }
        if (telefono.isEmpty()){
            binding.Phone.setHint("Escriba su telefono")
            binding.Phone.setHintTextColor(Color.RED)
        }
        if (correo.isEmpty()){
            binding.Email.setHint("Escriba su correo")
            binding.Email.setHintTextColor(Color.RED)
        }

        if (correo.isEmpty()){
            binding.Email.setHint("Escriba su correo")
            binding.Email.setHintTextColor(Color.RED)
        }
        if (cotrasena.isEmpty()){
            binding.sPassword.setHint("Escriba una contraseña")
            binding.sPassword.setHintTextColor(Color.RED)
        }
        if (ccotrasena.isEmpty()){
            binding.cPassword.setHint("Confirme su contraseña")
            binding.cPassword.setHintTextColor(Color.RED)
        }


        if (nombres!=""&&apellidos!=""&&direccion!=""&&telefono!=""&&correo!=""&&cotrasena!=""&&ccotrasena!=""){
            if (cotrasena != ccotrasena) {
                Toast.makeText(this, "La contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }else{
                firebaseAuth.createUserWithEmailAndPassword(correo,cotrasena).addOnCompleteListener(this){
                        task ->
                    if(task.isSuccessful){
                        id = firebaseAuth.currentUser?.uid.toString()
                        val data = hashMapOf<String, String>(
                            "Nombres" to nombres,
                            "Apellidos" to apellidos,
                            "Telefono" to telefono,
                            "Direccion" to direccion,

                        )
                        firestoreBd.collection("Usuarios").document(id).set(data).addOnSuccessListener {
                            task ->
                            Toast.makeText(this, "Usuario Registrado Exitosamente", Toast.LENGTH_LONG).show()
                            val intent: Intent = Intent(this, login::class.java)
                            startActivity(intent)
                        }.addOnFailureListener {
                            error ->
                            Toast.makeText(this, "Error al registrarse $error", Toast.LENGTH_LONG).show()
                        }

                    }else{
                        Toast.makeText(this, "Error al registrarse", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }
}