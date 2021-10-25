package com.juanjose.reto1kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juanjose.reto1kotlin.databinding.FragmentPublicacionesFragmentBinding


class Publicaciones_fragment : Fragment() {

    private lateinit var binding : FragmentPublicacionesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPublicacionesFragmentBinding.inflate(inflater,container,false)
        var view = binding.root

        binding.crearPublicacionBtn2.setOnClickListener {

            (activity as MainActivity).showFragment((activity as MainActivity).publicationInformation)

        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = Publicaciones_fragment()
    }
}