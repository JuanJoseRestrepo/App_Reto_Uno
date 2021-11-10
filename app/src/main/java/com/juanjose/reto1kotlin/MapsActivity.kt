package com.juanjose.reto1kotlin

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.juanjose.reto1kotlin.databinding.ActivityMapsBinding
import kotlinx.android.synthetic.main.fragment_publicaciones_informacion.*
import java.util.*
import android.content.Intent




class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener,
    Publicaciones_informacion.OnNewMapActivityListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var manager: LocationManager
    private lateinit var myMarker: Marker
    private lateinit var perfilFragment : MapsActivity

    private  var globalName: String? = null

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
            myMarker.snippet = getAddress(pos.latitude,pos.longitude)
            myMarker.isVisible = true
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos,17f))
        }

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000 ,2f,this)
    }

    @SuppressLint("MissingPermission")
    fun colombiaPosition(){
        val pos = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        if(pos != null){
            myMarker = putNewMarker(pos.latitude,pos.longitude)
            myMarker.isVisible = false
        }else{
            myMarker = putNewMarker(3.4,-72.0)
            myMarker.isVisible = false
        }

    }


    fun putNewMarker(latitude: Double, longitude: Double): Marker{
        // Add a marker in Sydney and move the camera
        val pos = LatLng(latitude, longitude)
        var marker: Marker? = null
        //Toast.makeText(this,globalName.toString(), Toast.LENGTH_SHORT).show()
        if(globalName != null){
             marker = mMap.addMarker(MarkerOptions().position(pos).title(globalName).snippet(getAddress(pos.latitude,pos.longitude)))
        }else{
             marker = mMap.addMarker(MarkerOptions().position(pos).title("holi").snippet(getAddress(pos.latitude,pos.longitude)))
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLng(pos))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos,17f))
        return marker
    }



    interface OnMapsActivity{
        fun onProfileEdit(marcador:Marker)
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

    companion object {
        @JvmStatic
        fun newInstance() = MapsActivity()
    }



    override fun OnNewPostMap(address: String) {
        this.globalName = address.toString()
    }

    override fun onResume() {
        super.onResume()

        //DETERMINE WHO STARTED THIS ACTIVITY
        val sender = this.intent.extras!!.getString("SENDER_KEY")

        //IF ITS THE FRAGMENT THEN RECEIVE DATA
        if (sender != null) {
            this.receiveData()
            Toast.makeText(this, "Received", Toast.LENGTH_SHORT).show()
        }
    }

    private fun receiveData() {
        //RECEIVE DATA VIA INTENT
        val i = intent
        val name = i.getStringExtra("NAME_KEY")

        //SET DATA TO TEXTVIEWS
        globalName = name
    }


}