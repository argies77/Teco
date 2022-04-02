package com.openBank.model

class Thumbnail(
    val path: String,
    var extension: String
){
fun getUrl() = "$path.$extension".replace("http", "https")
}