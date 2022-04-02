package com.openBank.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.openBank.AppExecutors
import com.openBank.api.DetailsService

import com.openBank.localStorage.BaseLocalStorage
import com.openBank.model.ApiMarvelRes
import com.openBank.model.KeyLocalStorage
import com.openBank.model.Resource
import com.openBank.network.ApiResponse

class DetailsRepository(
    private val detailsService: DetailsService,
    private val appExecutors: AppExecutors,
    private val baseLocalStorage: BaseLocalStorage
) {

    private val classNameMarvelCharacter = ApiMarvelRes::class.java

    fun getCharacter(id:Int,key: KeyLocalStorage): LiveData<Resource<ApiMarvelRes>> {
        return object : NetworkBoundResource<ApiMarvelRes, ApiMarvelRes>(appExecutors) {
            override fun saveCallResult(item: ApiMarvelRes) {
                baseLocalStorage.store(key, item)
            }

            override fun shouldFetch(data: ApiMarvelRes?) = true

            override fun loadFromLocal(): LiveData<ApiMarvelRes> {
                return MutableLiveData(baseLocalStorage.retrieve(key, classNameMarvelCharacter))
            }

            override fun createCall(): LiveData<ApiResponse<ApiMarvelRes>> =detailsService.getDetails(id)
        }.asLiveData()
    }
}