package com.juanjose.reto1kotlin

import android.annotation.SuppressLint
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.juanjose.reto1kotlin.databinding.ActivityMapsBinding
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var manager: LocationManager
    private lateinit var myMarker: Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        manager = getSystemService(LOCATION_SERVICE) as LocationManager

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        colombiaPosition()
        mMap.setOnMapLongClickListener {
            pos->
            myMarker.position = pos

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos,17f))
        }

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000 ,2f,this)
    }

    @SuppressLint("MissingPermission")
    fun colombiaPosition(){
        val pos = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        if(pos != null){
            myMarker = putNewMarker(pos.latitude,pos.longitude)
        }else{
            myMarker = putNewMarker(3.4,-72.0)
        }

    }


    fun putNewMarker(latitude: Double, longitude: Double): Marker{
        // Add a marker in Sydney and move the camera
        val pos = LatLng(latitude, longitude)
        val marker = mMap.addMarker(MarkerOptions().position(pos).title("Marker in Sydney").snippet(getAddress(pos.latitude,pos.longitude)))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(pos))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos,17f))
        return marker
    }



    interface OnMapsActivitu{
        fun onProfileEdit(name:String, photo: Uri, description:String)
    }



    fun getAddress(lat: Double, lng: Double): String{
        val geoCoder = Geocoder(this, Locale.getDefault())
        val adress = geoCoder.getFromLocation(lat,lng,1)
        return adress[0].getAddressLine(0).toString()
    }
    override fun onLocationChanged(p0: Location) {
        editMarkerPosition(myMarker,p0.latitude,p0.longitude)
    }

    private fun editMarkerPosition(myMarker: Marker, latitude: Double, longitude: Double) {
        val pos = LatLng(latitude,longitude)
        myMarker.position = pos
        mMap.animateCamera(CameraUpdateFactory.newLatLng(pos)                                                                             )
    }


}