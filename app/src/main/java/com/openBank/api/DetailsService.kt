package com.openBank.api

import androidx.lifecycle.LiveData
import com.openBank.model.ApiMarvelRes
import com.openBank.network.ApiResponse
import com.openBank.util.MarvelKeys
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {
    @GET("/v1/public/characters/{id}?apikey=${MarvelKeys.API_PUBLIC_KEY}&ts=${MarvelKeys.API_TS}&hash=${MarvelKeys.API_HASH}")
    //https://gateway.marvel.com:443/v1/public/characters/1011334?apikey=6fe3bca8e3cb4cd22096dd2cb94568f5
    fun getDetails(
        @Path("id") id: Int
    ):  LiveData<ApiResponse<ApiMarvelRes>>
}