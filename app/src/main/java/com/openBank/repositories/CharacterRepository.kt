package com.openBank.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.openBank.AppExecutors
import com.openBank.api.CharacterService
import com.openBank.localStorage.BaseLocalStorage
import com.openBank.model.ApiMarvelRes
import com.openBank.model.KeyLocalStorage
import com.openBank.model.Resource
import com.openBank.network.ApiResponse

class CharacterRepository (
    private val characterService: CharacterService,
    private val appExecutors: AppExecutors,
    private val baseLocalStorage: BaseLocalStorage
)  {

    private val classNameMarvelCharacter = ApiMarvelRes::class.java

  /*  fun getCharacterList(key: KeyLocalStorage): LiveData<Resource<List<Character>>> {
        return object : NetworkBoundResource<List<Character>, List<Character>>(appExecutors) {
            override fun saveCallResult(items: List<Character>) {
                baseLocalStorage.storeList(key,items)
            }

            override fun shouldFetch(data: List<Character>?)= true

            override fun loadFromLocal(): LiveData<List<Character>> {
                return MutableLiveData(baseLocalStorage.retrieveList(key,className))
            }

            override fun createCall(): LiveData<ApiResponse<List<Character>>> = characterService.getCharacterList()

        }.asLiveData()
    }*/

    fun getCharacterList(key: KeyLocalStorage): LiveData<Resource<ApiMarvelRes>> {
        return object : NetworkBoundResource<ApiMarvelRes, ApiMarvelRes>(appExecutors) {
            override fun saveCallResult(item: ApiMarvelRes) {
                baseLocalStorage.store(key, item)
            }

            override fun shouldFetch(data: ApiMarvelRes?) = true

            override fun loadFromLocal(): LiveData<ApiMarvelRes> {
                return MutableLiveData(baseLocalStorage.retrieve(key, classNameMarvelCharacter))
            }

            override fun createCall(): LiveData<ApiResponse<ApiMarvelRes>> = characterService.getCharacterList()
        }.asLiveData()
    }



}