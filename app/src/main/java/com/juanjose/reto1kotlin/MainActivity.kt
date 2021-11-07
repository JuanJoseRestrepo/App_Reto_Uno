package com.juanjose.reto1kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.SupportMapFragment
import com.juanjose.reto1kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    public lateinit var perfilFragment : Perfil_fragment
    public lateinit var publicationFragment : Publicaciones_fragment
    public lateinit var editProfileFragment : Editar_perfil
    public lateinit var publicationInformation : Publicaciones_informacion
    public lateinit var mapsGoogleFrag: MapsFragmentGoogle
    private lateinit var binding : ActivityMainBinding
    private lateinit var supp : SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        perfilFragment = Perfil_fragment.newInstance()
        publicationFragment = Publicaciones_fragment.newInstance()
        editProfileFragment = Editar_perfil.newInstance()
        editProfileFragment.listener = perfilFragment
        publicationInformation = Publicaciones_informacion.newInstance()
        mapsGoogleFrag = MapsFragmentGoogle.newInstance()


        showFragment(perfilFragment)

        binding.navegationBar.setOnItemSelectedListener { menuItem ->

            if(menuItem.itemId == R.id.navegation_perfil){
                showFragment(perfilFragment)
            }else if(menuItem.itemId == R.id.navegation_publicaciones){
                showFragment(publicationFragment)
            }else if(menuItem.itemId == R.id.navegation_mapa){
                showFragment(mapsGoogleFrag)
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