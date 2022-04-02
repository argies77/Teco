package com.openBank.di

import android.content.Context
import com.openBank.AppExecutors
import com.openBank.ui.character.CharacterViewModel
import com.openBank.ui.details.DetailsCharacterViewModel
import com.openBank.util.SharedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val SP_FILE = "user_preferences"

@JvmField
val appModule = module {

    single { AppExecutors() }
    viewModel{ CharacterViewModel(get()) }
    viewModel{ DetailsCharacterViewModel(get()) }
    viewModel { SharedViewModel() }
    single { androidContext().getSharedPreferences(SP_FILE, Context.MODE_PRIVATE) }

}

