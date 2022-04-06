package com.telecom.di

import com.telecom.api.DetailDeviceService
import com.telecom.api.DeviceService
import org.koin.dsl.module
import retrofit2.Retrofit

val telecomApiModule = module {

    fun provideDeviceApi(retrofit: Retrofit): DeviceService {
        return retrofit.create(DeviceService::class.java)
    }
    fun provideDetailDeviceCharacterApi(retrofit: Retrofit): DetailDeviceService {
        return retrofit.create(DetailDeviceService::class.java)
    }
    single { provideDeviceApi(get())}
    single { provideDetailDeviceCharacterApi(get())}
}