package com.telecom.ui.devices

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.telecom.model.Device
import com.telecom.model.KeyLocalStorage
import com.telecom.model.Resource
import com.telecom.repositories.DetailDeviceRepository
import com.telecom.repositories.DeviceRepository

class DeviceViewModel (
    private val deviceDetailRepository: DetailDeviceRepository,
    private val deviceRepository: DeviceRepository
) : ViewModel(){

    fun getDeviceList(): LiveData<Resource<List<Device>>> {
        return deviceRepository.getDeviceList(KeyLocalStorage.DEVICE)
    }

    fun getDeviceDetail(id:String): LiveData<Resource<Device>> {
        return deviceDetailRepository.getDeviceDetail(id,KeyLocalStorage.DETAIL_DEVICE)
    }
}