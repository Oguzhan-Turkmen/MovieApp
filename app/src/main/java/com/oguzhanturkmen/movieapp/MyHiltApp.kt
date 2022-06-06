package com.oguzhanturkmen.movieapp

import android.app.Application
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class MyHiltApp: Application() {
}