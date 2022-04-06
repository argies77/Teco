package com.telecom.model

class Device(val id: String,
             val name:String,
             val installmentsTag:String,
             val topTag:String,
             val mainImage:MainImage,
             val legal:String,
             val images: List<Images>)