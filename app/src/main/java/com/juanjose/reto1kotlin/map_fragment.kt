package com.juanjose.reto1kotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.size
import com.juanjose.reto1kotlin.databinding.FragmentMapFragmentBinding
import kotlinx.android.synthetic.main.fragment_map_fragment.*

class map_fragment : Fragment() {

    private lateinit var binding : FragmentMapFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapFragmentBinding.inflate(inflater,container,false)
        var view = binding.root

        binding.recyclerView.size
        binding.crearPublicacionBtn.positi
        // Inflate the layout for this fragment
        return view
    }

    companion object {


        @JvmStatic
        fun newInstance() = map_fragment()
    }
}