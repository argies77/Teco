package com.telecom.di


import com.telecom.repositories.DetailDeviceRepository
import com.telecom.repositories.DeviceRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { DeviceRepository(get(),get(),get()) }
    single { DetailDeviceRepository(get(),get(),get()) }
}

