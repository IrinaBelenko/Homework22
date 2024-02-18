package com.example.homework22

import com.google.gson.annotations.SerializedName

data class MeteoResponse(
    val latitude: Long,
    val longitude: Long,
    @SerializedName("generationtime_ms")
    val generationtimems: Double,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    val elevation: Long
)
