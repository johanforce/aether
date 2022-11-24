/*
 * Copyright © OMRON HEALTHCARE Co., Ltd. 2020. All rights reserved.
 */

package com.jarvis.weatherj.data.remote

import com.jarvis.weatherj.domain.model.response.WeatherResponse
import retrofit2.http.POST
import retrofit2.http.Path
import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.weatherj.presentation.base.data.ErrorResponse

interface WeatherApi {
    @POST("/{city}?format=j1")
    suspend fun fetchDataWeatherByCity(@Path("city") city: String): NetworkResponse<WeatherResponse, ErrorResponse>

    companion object {
        const val WEATHER_API_URL = "http://wttr.in/"
    }
}
