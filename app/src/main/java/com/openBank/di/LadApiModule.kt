package com.openBank.di

import com.openBank.api.CharacterService
import com.openBank.api.DetailsService
import org.koin.dsl.module
import retrofit2.Retrofit

val ladApiModule = module {

    fun provideCharacterApi(retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }
    fun provideDetailsCharacterApi(retrofit: Retrofit): DetailsService {
        return retrofit.create(DetailsService::class.java)
    }
    single { provideCharacterApi(get())}
    single { provideDetailsCharacterApi(get())}
}