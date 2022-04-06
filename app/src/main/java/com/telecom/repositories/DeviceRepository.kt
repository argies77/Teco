package com.telecom.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.telecom.AppExecutors
import com.telecom.api.DeviceService
import com.telecom.localStorage.BaseLocalStorage
import com.telecom.model.Device
import com.telecom.model.KeyLocalStorage
import com.telecom.model.Resource
import com.telecom.network.ApiResponse

class DeviceRepository (
    private val deviceService: DeviceService,
    private val appExecutors: AppExecutors,
    private val baseLocalStorage: BaseLocalStorage
)  {
    private val className = Device::class.java

    fun getDeviceList(key: KeyLocalStorage): LiveData<Resource<List<Device>>> {
        return object : NetworkBoundResource<List<Device>, List<Device>>(appExecutors) {
            override fun saveCallResult(items: List<Device>) {
                baseLocalStorage.storeList(key,items)
            }

            override fun shouldFetch(data: List<Device>?)= true

            override fun loadFromLocal(): LiveData<List<Device>> {
                return MutableLiveData(baseLocalStorage.retrieveList(key,className))
            }

            override fun createCall(): LiveData<ApiResponse<List<Device>>> = deviceService.getDeviceList()
        }.asLiveData()
    }

}