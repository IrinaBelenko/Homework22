package com.example.homework22

import retrofit2.Response

class Repository(private val apiClient: ApiClient) {
    suspend fun getMeteo2(): Response<MeteoResponse> {
        val apiInterface = apiClient.client.create(ApiInterface::class.java)
        return apiInterface.getMeteo()
    }
}