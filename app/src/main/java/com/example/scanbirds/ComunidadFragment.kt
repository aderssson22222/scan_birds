package com.example.scanbirds

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scanbirds.databinding.ActivityLoginBinding
import com.example.scanbirds.databinding.FragmentComunidadBinding
import com.google.firebase.firestore.FirebaseFirestore


class ComunidadFragment : Fragment(R.layout.fragment_comunidad) , ComunidadRecicler.clickComunidad {

    private var _binding: FragmentComunidadBinding? = null
    private val binding get() = _binding !!

    private  var list: MutableList<ComunidadItems> = mutableListOf()
    private  lateinit var recycler: RecyclerView

    lateinit var firestoreBd: FirebaseFirestore

    lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComunidadBinding.inflate(inflater)
        var view: FrameLayout = binding.root
        firestoreBd = FirebaseFirestore.getInstance()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intent=Intent(context,ComunidadDetallesActivity::class.java)
        firestoreBd.collection("Usuarios").get().addOnSuccessListener {

            resultado->
            for(doc in resultado){
                var Nombres = doc.getString("Nombres") as String
                var Apellidos = doc.getString("Apellidos") as String
                var Avez = doc.getString("Avez") as String
                var Rango = doc.getString("Rango") as String
                var Foto = doc.getString("Foto") as String
               var com = ComunidadItems(
                   "$Nombres",
                   "$Apellidos",
                   "$Avez Aves",
                   "Rango: $Rango",
                   "$Foto"
               )
                list.add(com)
            }

            binding.comunityList.apply {
                layoutManager=LinearLayoutManager(activity)
                adapter= ComunidadRecicler(list, this@ComunidadFragment)
            }

        }
        /*list.add(ComunidadItems("ROYER ANDERSSON", "CASTRO CHAURA","25 Avez","Rango: Maestro", "https://firebasestorage.googleapis.com/v0/b/scan-birds-fcdb7.appspot.com/o/img_usuarios%2Fchesca.png?alt=media&token=73abc6e1-9343-4d8d-9f9b-7ce5e85f13bc"))
        list.add(ComunidadItems("MISH", "CASTRO CHAURA","26 Avez","Rango: Maestro1", "https://firebasestorage.googleapis.com/v0/b/scan-birds-fcdb7.appspot.com/o/img_usuarios%2Fchesca.png?alt=media&token=73abc6e1-9343-4d8d-9f9b-7ce5e85f13bc"))
        list.add(ComunidadItems("VALKY", "CASTRO CHAURA","27 Avez","Rango: Maestro5", "https://firebasestorage.googleapis.com/v0/b/scan-birds-fcdb7.appspot.com/o/img_usuarios%2Fchesca.png?alt=media&token=73abc6e1-9343-4d8d-9f9b-7ce5e85f13bc"))
        list.add(ComunidadItems("MAIRA ALEJANDRA", "SANABRIA CAMARGO","28 Avez","Rango: Maestro2", "https://firebasestorage.googleapis.com/v0/b/scan-birds-fcdb7.appspot.com/o/img_usuarios%2Fchesca.png?alt=media&token=73abc6e1-9343-4d8d-9f9b-7ce5e85f13bc"))
        list.add(ComunidadItems("CHESCA", "CASTRO CHAURA","29 Avez","Rango: Maestro3", "https://firebasestorage.googleapis.com/v0/b/scan-birds-fcdb7.appspot.com/o/img_usuarios%2Falisson.png?alt=media&token=ed1a4df5-ab86-4200-b90c-efc270d5518c"))
        list.add(ComunidadItems("ALISSON", "CASTRO CHAURA","30 Avez","Rango: Maestro4", "https://firebasestorage.googleapis.com/v0/b/scan-birds-fcdb7.appspot.com/o/img_usuarios%2Falisson.png?alt=media&token=ed1a4df5-ab86-4200-b90c-efc270d5518c"))

        binding.comunityList.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter= ComunidadRecicler(list)
        }*/

    }

    override fun onItemClick(comunidad: ComunidadItems) {

        intent.putExtra("Nombres",comunidad.nombres)
        intent.putExtra("Apellidos",comunidad.apellidos)
        intent.putExtra("Aves",comunidad.avez)
        intent.putExtra("Rango",comunidad.rango)
        intent.putExtra("Avatar",comunidad.avatar)
        startActivity(intent)
        Toast.makeText(context,"${comunidad.nombres}", Toast.LENGTH_LONG).show()
    }


}