package com.stech.data.api

import com.stech.data.api.model.Attatchments
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEndPoint {
    @GET("https://nagwa.free.beeceptor.com/movies")
    suspend fun getAttached(): Response<List<Attatchments>>
}