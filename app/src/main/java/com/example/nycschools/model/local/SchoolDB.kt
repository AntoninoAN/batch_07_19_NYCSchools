package com.example.nycschools.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SchoolEntity::class,
SatEntity::class, SchoolSatEntity::class],
version = 1,
    exportSchema = true)
abstract class SchoolDB : RoomDatabase() {

    abstract fun getDao(): NYCDao

    companion object {
        @Volatile private var INSTANCE:
        SchoolDB? = null

        fun getInstance(context: Context): SchoolDB = INSTANCE ?: synchronized(this){
            var temp = INSTANCE
            if (temp != null) return temp
            
            temp = Room.databaseBuilder(
                context,
                SchoolDB::class.java,
                "school_db"
            ).build()

            INSTANCE = temp
            temp
        }
    }
}