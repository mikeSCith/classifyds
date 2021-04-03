package com.mikesmithinc.api

import retrofit2.http.GET

interface ApiService {

    @GET("header")
    suspend fun getHeader(): List<String>
}