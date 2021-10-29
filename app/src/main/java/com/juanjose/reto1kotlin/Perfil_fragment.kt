package com.juanjose.reto1kotlin

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juanjose.reto1kotlin.databinding.FragmentPerfilFragmentBinding
import kotlinx.android.synthetic.main.fragment_perfil_fragment.*


class Perfil_fragment : Fragment(), Editar_perfil.OnProfileEdit {

    private lateinit var binding: FragmentPerfilFragmentBinding
    private var name: String = "Nombre por defecto"
    private var  photo: Uri? = null
    private var description: String = "Descripcion"
    private lateinit var perfilFragment : Perfil_fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        perfilFragment = Perfil_fragment.newInstance()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        val oldname = sharedPreferences.getString("NAME", "Inserte el nombre")
        val olddescription = sharedPreferences.getString("DESCRIPTION", "Inserte una descripcion")
        val oldimage = sharedPreferences.getString("IMAGE", null)

        binding.namePersonTV.text = oldname
        binding.descriptionPersonTV.text = olddescription

        if(oldimage != null){
            binding.profilePhotoIV.setImageURI(Uri.parse(oldimage))
        }

        editBtn.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val editProfileFragment = Editar_perfil.newInstance()
            transaction.replace(R.id.fragment_container, editProfileFragment)
            transaction.addToBackStack("perfilFragment")
            transaction.commit()
        }

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