package com.example.nycschools.di.modules

import com.example.nycschools.model.Repository
import com.example.nycschools.model.local.NYCDao
import com.example.nycschools.model.remote.Network
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(
        nycDao: NYCDao,
        network: Network,
        scope: CoroutineScope
    ): Repository{
        return Repository(nycDao, network, scope)
    }
}