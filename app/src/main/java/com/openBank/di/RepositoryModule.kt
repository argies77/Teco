package com.openBank.di


import com.openBank.repositories.CharacterRepository
import com.openBank.repositories.DetailsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CharacterRepository(get(),get(),get()) }
    single { DetailsRepository(get(),get(),get()) }
}

