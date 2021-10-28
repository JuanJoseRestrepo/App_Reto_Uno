package com.juanjose.reto1kotlin

import android.net.Uri

class Post {

    var id : String
    var address : String
    var startDate : String
    var endDate : String
    var photo : Uri
    var eventName : String
    var placeName : String

    constructor(id : String, address : String, startDate : String, endDate : String, photo : Uri, eventName : String, placeName : String){
        this.id = id
        this.address = address
        this.startDate = startDate
        this.endDate = endDate
        this.photo = photo
        this.eventName = eventName
        this.placeName = placeName
    }

}