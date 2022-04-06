package com.telecom.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.telecom.AppExecutors
import com.telecom.api.DetailDeviceService
import com.telecom.localStorage.BaseLocalStorage
import com.telecom.model.Device
import com.telecom.model.KeyLocalStorage
import com.telecom.model.Resource
import com.telecom.network.ApiResponse

class DetailDeviceRepository(
    private val detailDeviceService: DetailDeviceService,
    private val appExecutors: AppExecutors,
    private val baseLocalStorage: BaseLocalStorage
) {

    private val classNameMarvelCharacter = Device::class.java

    fun getDeviceDetail(id:String,key: KeyLocalStorage): LiveData<Resource<Device>> {
        return object : NetworkBoundResource<Device, Device>(appExecutors) {
            override fun saveCallResult(item: Device) {
                baseLocalStorage.store(key, item)
            }

            override fun shouldFetch(data: Device?) = true

            override fun loadFromLocal(): LiveData<Device> {
                return MutableLiveData(baseLocalStorage.retrieve(key, classNameMarvelCharacter))
            }

            override fun createCall(): LiveData<ApiResponse<Device>> =detailDeviceService.getDeviceDetail(id)
        }.asLiveData()
    }
}