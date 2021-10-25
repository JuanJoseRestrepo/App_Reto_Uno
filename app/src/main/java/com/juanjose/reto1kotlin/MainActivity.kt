package com.juanjose.reto1kotlin

import android.Manifest
import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.SupportMapFragment
import com.juanjose.reto1kotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    public lateinit var perfilFragment : Perfil_fragment
    public lateinit var publicationFragment : Publicaciones_fragment
    public lateinit var editProfileFragment : Editar_perfil
    private lateinit var mapaFragment: MapsFragmentGoogle
    private lateinit var binding : ActivityMainBinding
    private lateinit var supp : SupportMapFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissions(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ),1)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        perfilFragment = Perfil_fragment.newInstance()
        publicationFragment = Publicaciones_fragment.newInstance()
        editProfileFragment = Editar_perfil.newInstance()
        editProfileFragment.listener = perfilFragment

        //supp
        //mapaFragment = MapsFragmentGoogle.
        //navegation_mapa

        showFragment(perfilFragment)

        binding.navegationBar.setOnItemSelectedListener { menuItem ->

            if(menuItem.itemId == R.id.navegation_perfil){
                showFragment(perfilFragment)
            }else if(menuItem.itemId == R.id.navegation_publicaciones){
                showFragment(publicationFragment)
            }

            true

        }

    }

    fun showFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }



}