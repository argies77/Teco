package com.openBank.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.openBank.model.ApiMarvelRes
import com.openBank.model.KeyLocalStorage
import com.openBank.model.Resource
import com.openBank.repositories.CharacterRepository

class CharacterViewModel (
    private val characterRepository: CharacterRepository
) : ViewModel(){


    fun getCharacterList(key: KeyLocalStorage): LiveData<Resource<ApiMarvelRes>> {
        return characterRepository.getCharacterList(key)
    }
}