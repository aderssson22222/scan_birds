package com.example.scanbirds

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
import java.io.File

class scan : AppCompatActivity() {
    lateinit var binding: ActivityScanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")
        Toast.makeText(this, "Bienvenid@ a SCAN BIRDS $email", Toast.LENGTH_LONG).show()

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
            val nombreAve:String = binding.nombreAve.toString()
            val departamentoAve:String = binding.departamentoAve.toString()
            val userPicture:String=("culito")

        }
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