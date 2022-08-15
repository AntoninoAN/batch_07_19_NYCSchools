package com.example.nycschools.di.modules

import com.example.nycschools.model.remote.Network
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesService() = Network()
}