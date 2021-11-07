package com.juanjose.reto1kotlin

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragmentGoogle : Fragment(){

    private lateinit var manager: LocationManager
    private lateinit var markers :ArrayList<Marker>

    private val callback = OnMapReadyCallback { googleMap ->
        val arraylists = ArrayList<Marker>()
        markers = arraylists

        googleMap.setOnMapClickListener { pos->
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos,14f))
        }

        googleMap.setOnMapLongClickListener {
            pos->
            val marker = putNeMarket(pos.latitude,pos.longitude,googleMap)
            markers.add(marker)
        }


        putNeMarket(3.4,-72.0,googleMap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps_google, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }

    companion object {
        fun newInstance(): MapsFragmentGoogle {
            val args = Bundle()
            val fragment = MapsFragmentGoogle()
            fragment.arguments = args
            return fragment
        }
    }

    fun putNeMarket(lat:Double,lng:Double,googlemap: GoogleMap): Marker {
        val sydney = LatLng(lat, lng)
        val marker = googlemap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googlemap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        return marker
    }

}