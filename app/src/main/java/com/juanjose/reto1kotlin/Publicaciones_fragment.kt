package com.juanjose.reto1kotlin

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanjose.reto1kotlin.databinding.FragmentPublicacionesFragmentBinding
import java.util.*


class Publicaciones_fragment : Fragment() , Publicaciones_informacion.OnNewPostListener{

    private var _binding : FragmentPublicacionesFragmentBinding? = null
    private val binding get() = _binding!!

    //STATE
    private val adapter = PostsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPublicacionesFragmentBinding.inflate(inflater,container,false)
        var view = binding.root

        //Recrear el estado
        val postRecycler = binding.postRecycler
        postRecycler.setHasFixedSize(true)
        postRecycler.layoutManager = LinearLayoutManager(activity)
        postRecycler.adapter = adapter

        binding.crearPublicacionBtn2.setOnClickListener {

            (activity as MainActivity).showFragment((activity as MainActivity).publicationInformation)

        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = Publicaciones_fragment()
    }

    override fun OnNewPost(address : String, startDate : String, endDate : String, photo : Uri, eventName : String, placeName : String) {

        //Modificacion del estado
        val newPost = Post(UUID.randomUUID().toString(), address, startDate, endDate, photo, eventName, placeName)
        adapter.addPost(newPost)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}