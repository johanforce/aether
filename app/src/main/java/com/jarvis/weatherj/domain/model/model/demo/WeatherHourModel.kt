package com.jarvis.weatherj.domain.model.model.demo

import com.jarvis.kmm.dto.WeatherHourDto

data class WeatherHourModel(
    var time: String? = null,
    var tempC: String? = null,
    var weatherCode: String? = null
)
