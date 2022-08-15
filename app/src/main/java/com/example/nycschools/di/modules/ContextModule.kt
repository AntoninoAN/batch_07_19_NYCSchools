package com.example.nycschools.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Assisted Injection.
 */
@Module
class ContextModule(private val context: Context) {
    @Provides
    fun provideContext(): Context{
        return context
    }
}