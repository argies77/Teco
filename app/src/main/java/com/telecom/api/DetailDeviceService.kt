package com.telecom.api

import androidx.lifecycle.LiveData
import com.telecom.model.Device
import com.telecom.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailDeviceService {

    @GET("/devices/{id}")
    fun getDeviceDetail(
        @Path(
            value = "id",
            encoded = true
        ) id: String
    ): LiveData<ApiResponse<Device>>


}