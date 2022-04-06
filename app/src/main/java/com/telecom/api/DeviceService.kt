package com.telecom.api

import androidx.lifecycle.LiveData
import com.telecom.model.Device
import com.telecom.network.ApiResponse
import retrofit2.http.GET

interface DeviceService {
    @GET("/devices")
    fun getDeviceList(
    ): LiveData<ApiResponse<List<Device>>>
}