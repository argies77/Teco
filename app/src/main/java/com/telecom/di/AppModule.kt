package com.telecom.di

import android.content.Context
import com.telecom.AppExecutors
import com.telecom.ui.devices.DeviceViewModel
import com.telecom.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val SP_FILE = "user_preferences"

@JvmField
val appModule = module {

    single { AppExecutors() }
    viewModel{ DeviceViewModel(get(),get()) }
    viewModel{ SplashViewModel() }
    single { androidContext().getSharedPreferences(SP_FILE, Context.MODE_PRIVATE) }

}

