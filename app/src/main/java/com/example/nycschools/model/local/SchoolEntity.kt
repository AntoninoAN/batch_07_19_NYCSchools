package com.example.nycschools.model.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "school_table")
data class SchoolEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dbn: String,
    @ColumnInfo(name = "name")
    val school_name: String,
    val location: String,
    val phone_number: String,
    val fax_number: String,
    val city: String,
    val latitude: String,
    val longitude: String
)
@Entity
data class SatEntity(
    @PrimaryKey
    val dbn: String,
    val school_name: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String
)
@Entity(tableName = "school_sat")
@Parcelize
data class SchoolSatEntity(
    @PrimaryKey
    val dbn: String,
    val school_name: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String,
    val location: String,
    val phone_number: String,
    val fax_number: String,
    val city: String,
    val latitude: String,
    val longitude: String
): Parcelable