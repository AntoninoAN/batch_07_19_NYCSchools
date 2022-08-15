package com.example.nycschools.di

import android.app.Application
import com.example.nycschools.di.component.DaggerNycComponent
import com.example.nycschools.di.component.NycComponent
import com.example.nycschools.di.modules.*

class NYCApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        //[Dagger]+Component_Interface
        component =
        DaggerNycComponent.builder()
            .coroutineModule(CoroutineModule())
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .roomModule(RoomModule())
            .contextModule(ContextModule( applicationContext ))
            .build()
    }
    companion object{
        lateinit var component: NycComponent
    }
}