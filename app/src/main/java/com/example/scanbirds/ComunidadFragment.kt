package com.example.scanbirds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scanbirds.databinding.ActivityLoginBinding
import com.example.scanbirds.databinding.FragmentComunidadBinding


class ComunidadFragment : Fragment(R.layout.fragment_comunidad) {

    private var _binding: FragmentComunidadBinding? = null
    private val binding get() = _binding !!

    private  var list: MutableList<ComunidadItems> = mutableListOf()
    private  lateinit var recycler: RecyclerView

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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.add(ComunidadItems("ROYER ANDERSSON", "CASTRO CHAURA","25 Avez","Rango: Maestro", R.drawable.logo))
        list.add(ComunidadItems("MISH", "CASTRO CHAURA","26 Avez","Rango: Maestro1", R.drawable.logo))
        list.add(ComunidadItems("VALKY", "CASTRO CHAURA","27 Avez","Rango: Maestro5", R.drawable.border_round))
        list.add(ComunidadItems("MAIRA ALEJANDRA", "SANABRIA CAMARGO","28 Avez","Rango: Maestro2", R.drawable.logo))
        list.add(ComunidadItems("CHESCA", "CASTRO CHAURA","29 Avez","Rango: Maestro3", R.drawable.logo))
        list.add(ComunidadItems("ALISSON", "CASTRO CHAURA","30 Avez","Rango: Maestro4", R.drawable.logo))

        binding.comunityList.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter= ComunidadRecicler(list)
        }

    }


}