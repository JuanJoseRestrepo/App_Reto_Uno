package com.juanjose.reto1kotlin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.SupportMapFragment
import com.juanjose.reto1kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    public lateinit var perfilFragment : Perfil_fragment
    public lateinit var publicationFragment : Publicaciones_fragment
    public lateinit var editProfileFragment : Editar_perfil
    public lateinit var publicationInformation : Publicaciones_informacion
    public lateinit var mapsGoogleFrag: MapsFragmentGoogle
    public lateinit var mapsGoogleActi: MapsActivity
    private lateinit var binding : ActivityMainBinding
    private lateinit var supp : SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissions(arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE),1)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        perfilFragment = Perfil_fragment.newInstance()
        publicationFragment = Publicaciones_fragment.newInstance()
        editProfileFragment = Editar_perfil.newInstance()
        publicationInformation = Publicaciones_informacion.newInstance()
        mapsGoogleFrag = MapsFragmentGoogle.newInstance()
        //mapsGoogleActi = mapsGoogleActi.newInstance()
        //Suscripcion (Listeners)
        publicationInformation.listener = publicationFragment
        editProfileFragment.listener = perfilFragment


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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var allGranted = true

        for(result in grantResults){
            if(result == PackageManager.PERMISSION_DENIED){
                allGranted = false
                break
            }
        }

    }

    fun showFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }


}