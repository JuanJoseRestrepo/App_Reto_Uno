package com.juanjose.reto1kotlin

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juanjose.reto1kotlin.databinding.FragmentPerfilFragmentBinding


class Perfil_fragment : Fragment(), Editar_perfil.OnProfileEdit {

    private lateinit var binding: FragmentPerfilFragmentBinding
    private var name: String = "Nombre por defecto"
    private var  photo: Uri? = null
    private var description: String = "Descripcion "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilFragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.editBtn.setOnClickListener{

            (activity as MainActivity).showFragment((activity as MainActivity).editProfileFragment)


        }


        binding.descriptionPersonTV.text = this.description
        binding.namePersonTV.text = this.name
        if(this.photo != null){
            binding.profilePhotoIV.setImageURI(photo)
        }



        return view

    }

    companion object {
        @JvmStatic
        fun newInstance() = Perfil_fragment()
    }

    override fun onProfileEdit(name: String, photo: Uri, description: String) {
        this.name = name
        this.photo = photo
        this.description = description

    }



}