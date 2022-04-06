package com.telecom.di

import com.telecom.localStorage.BaseLocalStorage
import org.koin.dsl.module

val localStorageModule = module {
    single { BaseLocalStorage(get()) }
}
