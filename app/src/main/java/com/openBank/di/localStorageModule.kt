package com.openBank.di

import com.openBank.localStorage.BaseLocalStorage
import org.koin.dsl.module

val localStorageModule = module {
    single { BaseLocalStorage(get()) }
}
