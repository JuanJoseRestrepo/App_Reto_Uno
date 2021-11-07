package com.juanjose.reto1kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private val posts = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        //inflate
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.postrow, parent, false)
        val postViewHolder = PostViewHolder(view)
        return postViewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postn = posts[position]
        holder.addressTV.text = postn.address
        holder.endDateTV.text = postn.endDate
        holder.startDateTV.text = postn.startDate
        holder.eventNameTV.text = postn.eventName
        holder.placeNameTV.text = postn.placeName
        holder.photoIV.setImageURI(postn.photo)
    }

    fun addPost(post : Post){
        posts.add(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}