package com.openBank.api

import androidx.lifecycle.LiveData
import com.openBank.model.ApiMarvelRes
import com.openBank.network.ApiResponse
import com.openBank.util.MarvelKeys.API_HASH
import com.openBank.util.MarvelKeys.API_PUBLIC_KEY
import com.openBank.util.MarvelKeys.API_TS
import retrofit2.http.GET

interface CharacterService {
    @GET("/v1/public/characters?apikey=$API_PUBLIC_KEY&ts=$API_TS&hash=$API_HASH")
    fun getCharacterList(
    ): LiveData<ApiResponse<ApiMarvelRes>>
}