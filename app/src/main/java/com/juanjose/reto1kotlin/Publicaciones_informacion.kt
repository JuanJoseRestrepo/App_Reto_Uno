package com.juanjose.reto1kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juanjose.reto1kotlin.databinding.FragmentPublicacionesFragmentBinding

class Publicaciones_informacion : Fragment() {

    private var start : Long = 0
    private var end : Long = 0

    private lateinit var binding : FragmentPublicacionesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPublicacionesFragmentBinding.inflate(inflater,container,false)
        var view = binding.root



        // Inflate the layout for this fragment
        return view
    }

    fun defineStartDate(view: View){
       // showDatePicker()


    }

    fun showDatePicker(listener : DateFragment.OnDateSelectedListener){
        var dialog = DateFragment.newInstance()
        dialog.setListener(listener)
        activity?.let { dialog.show(it.supportFragmentManager, "dialog") }
    }


    companion object {
        @JvmStatic
        fun newInstance() = Publicaciones_informacion()
    }
}