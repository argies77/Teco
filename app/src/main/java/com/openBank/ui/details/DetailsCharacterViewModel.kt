package com.openBank.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.openBank.model.ApiMarvelRes
import com.openBank.model.KeyLocalStorage
import com.openBank.model.Resource
import com.openBank.repositories.DetailsRepository

class DetailsCharacterViewModel(
    private val detailsRepository: DetailsRepository
) : ViewModel() {
    fun getCharacter(id:Int): LiveData<Resource<ApiMarvelRes>> {
        return detailsRepository.getCharacter(id,KeyLocalStorage.CHARACTER)
    }
}