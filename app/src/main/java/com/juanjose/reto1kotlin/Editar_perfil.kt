package com.juanjose.reto1kotlin

import android.app.Instrumentation
import android.content.Intent
import android.content.Intent.*
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.juanjose.reto1kotlin.databinding.FragmentEditarPerfilBinding
import java.io.File


class Editar_perfil : Fragment() {

    private lateinit var binding: FragmentEditarPerfilBinding
    public lateinit var listener: OnProfileEdit
    private var file: File? = null

     override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditarPerfilBinding.inflate(inflater,container,false)
        val view = binding.root

         binding.imageButton2.setOnClickListener{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                startActivity(intent)
         }

        binding.acceptBtn.setOnClickListener{

            listener?.let {
                //it.onProfileEdit()
            }

        }

        return view
    }

    interface OnProfileEdit{
        fun onProfileEdit(name:String, photo: Bitmap, description:String)
    }

    companion object {
        @JvmStatic
        fun newInstance() = Editar_perfil()
    }



}