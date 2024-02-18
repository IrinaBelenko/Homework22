package com.example.homework22

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/v1/forecast?latitude=52&longitude=13")
    suspend fun getMeteo(): Response<MeteoResponse>
}

