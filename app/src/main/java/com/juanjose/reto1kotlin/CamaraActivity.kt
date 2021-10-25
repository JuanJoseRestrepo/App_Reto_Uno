package com.juanjose.reto1kotlin

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juanjose.reto1kotlin.databinding.ActivityCamaraBinding
import java.io.File

class CamaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCamaraBinding
    private var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camara)

        requestPermissions(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ),1)

        binding = ActivityCamaraBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




    }
}