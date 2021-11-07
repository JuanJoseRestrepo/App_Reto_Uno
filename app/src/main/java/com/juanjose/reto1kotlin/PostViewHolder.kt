package com.juanjose.reto1kotlin

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //UI Constrollers

    var photoIV : ImageView = itemView.findViewById(R.id.photoIV)
    var eventNameTV : TextView = itemView.findViewById(R.id.eventNameTV)
    var placeNameTV : TextView = itemView.findViewById(R.id.placeNameTV)
    var addressTV : TextView = itemView.findViewById(R.id.addressTV)
    var startDateTV : TextView = itemView.findViewById(R.id.startDateTV)
    var endDateTV : TextView = itemView.findViewById(R.id.endDateTV)
}