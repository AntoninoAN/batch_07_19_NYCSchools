package com.example.nycschools.di.component

import com.example.nycschools.MainActivity
import com.example.nycschools.di.modules.*
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CoroutineModule::class,
NetworkModule::class,
RepositoryModule::class,
RoomModule::class,
ContextModule::class])
@Singleton
interface NycComponent {
    fun inject( mainActivity: MainActivity )
}