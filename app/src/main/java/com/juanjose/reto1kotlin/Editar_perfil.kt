package com.juanjose.reto1kotlin

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juanjose.reto1kotlin.databinding.FragmentEditarPerfilBinding

class Editar_perfil : Fragment() {

    private lateinit var binding: FragmentEditarPerfilBinding
    public lateinit var listener: OnProfileEdit

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditarPerfilBinding.inflate(inflater,container,false)
        val view = binding.root

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