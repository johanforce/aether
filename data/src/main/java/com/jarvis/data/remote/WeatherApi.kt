package com.jarvis.data.remote

import com.jarvis.data.remote.response.DataResponse
import retrofit2.http.POST
import retrofit2.http.Path

interface WeatherApi {
    @POST("/{city}?format=j1")
    suspend fun fetchDataWeatherByCity(@Path("city") city: String? = null): DataResponse
}
