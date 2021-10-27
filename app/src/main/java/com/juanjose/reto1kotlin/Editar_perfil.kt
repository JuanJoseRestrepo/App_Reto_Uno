package com.juanjose.reto1kotlin

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import android.content.Intent.*
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.juanjose.reto1kotlin.databinding.FragmentEditarPerfilBinding
import kotlinx.android.synthetic.main.fragment_editar_perfil.*
import java.io.File


class Editar_perfil : Fragment() {

    private lateinit var binding: FragmentEditarPerfilBinding
    public lateinit var listener: OnProfileEdit
    private val PICK_IMAGE = 100
    private lateinit var imageUri: Uri


     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentEditarPerfilBinding.inflate(inflater,container,false)
        val view = binding.root

         binding.imageBtn.setOnClickListener{
                openGallery()
                imageBtn.isEnabled = true
         }

        binding.acceptBtn.setOnClickListener{
            if(!description.text.isEmpty() && !namePerson.text.isEmpty() && imageBtn.isEnabled){
                listener?.let {

                    it.onProfileEdit(namePerson.text.toString(),imageUri,description.text.toString())

                }

                (activity as MainActivity).showFragment((activity as MainActivity).perfilFragment)

            }
        }

        return view
    }


    interface OnProfileEdit{
        fun onProfileEdit(name:String, photo: Uri, description:String)
    }

    companion object {
        @JvmStatic
        fun newInstance() = Editar_perfil()
    }

    fun openGallery(){
        val value = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(value, PICK_IMAGE);
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data?.data!!
            imageBtn.setImageURI(imageUri)
        }
    }

}