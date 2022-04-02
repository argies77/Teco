package com.openBank.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _idCharacter = MutableLiveData<Int>()
    val idCharacter: LiveData<Int> = _idCharacter
    fun setIdCharacter(idCharacter: Int) {
        _idCharacter.value = idCharacter
    }


}