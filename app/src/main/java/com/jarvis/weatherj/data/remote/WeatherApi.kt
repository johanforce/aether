/*
 * Copyright © OMRON HEALTHCARE Co., Ltd. 2020. All rights reserved.
 */

package com.jarvis.weatherj.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.weatherj.domain.model.response.demo.DataResponse
import com.jarvis.weatherj.presentation.base.data.ErrorResponse
import retrofit2.http.POST
import retrofit2.http.Path

interface WeatherApi {
    @POST("/{city}?format=j1")
    suspend fun fetchDataWeatherByCity(@Path("city") city: String? = null): NetworkResponse<DataResponse, ErrorResponse>
}
