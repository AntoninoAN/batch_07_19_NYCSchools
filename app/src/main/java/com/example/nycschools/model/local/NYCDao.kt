package com.example.nycschools.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NYCDao {
    // fetch school list
    // fetch sat list
    // insert merged data
    // query schoolsat

    @Insert(entity = SchoolEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun fetchSchoolList(remoteSchoolList: SchoolEntity)

    @Insert(entity = SatEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun fetchSatList(remoteSatList: SatEntity)

    @Insert(entity = SchoolSatEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchoolSat(schoolSatEntity: SchoolSatEntity)

    @Query(value = "SELECT * FROM school_sat")
    suspend fun getPresentationData(): List<SchoolSatEntity>
}