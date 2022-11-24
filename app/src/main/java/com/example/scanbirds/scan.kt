package com.example.scanbirds

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.scanbirds.databinding.ActivityScanBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.File

class scan : AppCompatActivity() {
    lateinit var binding: ActivityScanBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth



        binding.btnCamera.setOnClickListener {
            //tomarFoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                crearFoto()
                val fotoUri=FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID+".fileProvider",file)
                it.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri)
            }
            tomarFoto.launch(intent)
        }

        binding.buttonSavePictureHall.setOnClickListener {
            saveBird()
        }
    }
    fun saveBird(){
        val nombreAve: String = binding.nombreAve.text.toString()
        val departamentoAve: String = binding.departamentoAve.text.toString()
        val foto: String = file.toString()
        val userPicture = firebaseAuth.currentUser?.email.toString()

        var pref = getSharedPreferences("${userPicture}_${nombreAve}", Context.MODE_PRIVATE)
        var edit = pref.edit()
        edit.putString("id_ave","${userPicture}_${nombreAve}")
        edit.putString("correo",userPicture)
        edit.putString("nombreAve",nombreAve)
        edit.putString("departamentoAve",departamentoAve)
        edit.putString("foto",foto)

        edit.commit()
        Toast.makeText(this, "Scaneada Ave con exito", Toast.LENGTH_LONG).show()
        val intent = Intent(this, ScanFragment::class.java)
        startActivity(intent)
    }
    val tomarFoto =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if (result.resultCode == RESULT_OK){
                val data = result.data!!
                //val bitmap = data.extras?.get("data") as Bitmap
                val bitmap = BitmapFactory.decodeFile(file.toString())
                binding.PictureCamBird.setImageBitmap(bitmap)
            }
        }
    private lateinit var file:File
    private fun crearFoto(){
        val dir= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        file = File.createTempFile("Foto_${System.currentTimeMillis()}_",".jpg",dir)
    }
}