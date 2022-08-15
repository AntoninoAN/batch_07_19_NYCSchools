package com.example.nycschools.di.modules

import android.content.Context
import com.example.nycschools.model.local.NYCDao
import com.example.nycschools.model.local.SchoolDB
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Modules contains dependencies that will be part of the
 * Object Graph.
 */
@Module
class RoomModule {
    /**
     * Provide such dependency. Concrete Object.
     * @Binds will be used for abstract types (abstract classes,
     * interfaces)
     */
    @Provides
    fun provideRoomDao(context: Context): NYCDao{
        return SchoolDB.getInstance(context).getDao()
    }

}