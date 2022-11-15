package com.example.scanbirds

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.scanbirds.databinding.FragmentScanBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ScanFragment : Fragment(R.layout.fragment_scan) {

    private var _binding : FragmentScanBinding? = null
    private val binding get() = _binding!!
    private var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScanBinding.inflate(inflater)
        var view: ConstraintLayout = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var intent = Intent(context, scan::class.java)
        if(arguments != null){
            val email = requireArguments().getString("email")
            intent.putExtra("email",email)
        }
        fab = binding.PictureBird
        fab!!.setOnClickListener {
            startActivity(intent)
        }
    }


}