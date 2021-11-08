package com.juanjose.reto1kotlin

import android.Manifest
import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.juanjose.reto1kotlin.databinding.FragmentEditarPerfilBinding
import kotlinx.android.synthetic.main.fragment_editar_perfil.*


class Editar_perfil : Fragment() {

    private lateinit var binding: FragmentEditarPerfilBinding
    public lateinit var listener: OnProfileEdit
    private val PICK_IMAGE = 100
    private lateinit var imageUri: Uri


     @SuppressLint("WrongConstant")
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
                    binding.imageBtn.isEnabled = false
                }

                (activity as MainActivity).showFragment((activity as MainActivity).perfilFragment)

            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.acceptBtn.setOnClickListener {

            val sharedPreferences = requireActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
            val spEditor = sharedPreferences.edit()

            val newname = binding.namePerson.text.toString()
            val newdescription = binding.description.text.toString()

            spEditor.apply{
                putString("NAME", newname)
                putString("DESCRIPTION", newdescription)
                putString("IMAGE", imageUri.toString())

            }.apply()

            requireActivity().supportFragmentManager.popBackStackImmediate()

        }




    }

    interface OnProfileEdit{
        fun onProfileEdit(name:String, photo: Uri, description:String)
    }

    companion object {

        private val IMAGE_PICK_CODE = 1000

        private val PERMISSION_CODE = 1001

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