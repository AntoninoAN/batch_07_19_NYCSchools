package com.example.nycschools.model.remote

import com.google.gson.annotations.SerializedName


data class NYCSchool(
    val dbn: String,
    val school_name: String? = "N/A",
    val location: String?= "N/A",
    val phone_number: String?,
    val fax_number: String?,
    val city: String?,
    val latitude: String?= "N/A",
    val longitude: String?= "N/A"
)

data class NYCSatScore(
    val dbn: String,
    val school_name: String,
    @SerializedName("num_of_sat_test_takers")
    val satTakers: String,
    @SerializedName("sat_critical_reading_avg_score")
    val reading: String,
    @SerializedName("sat_math_avg_score")
    val math: String,
    @SerializedName("sat_writing_avg_score")
    val writing: String
)

