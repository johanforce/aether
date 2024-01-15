package com.jarvis.weatherj.presentation.common

import com.jarvis.kmm.dto.WeatherHourDto
import com.jarvis.weatherj.domain.model.model.demo.WeatherHourModel

class KmmMappingHelper {
    fun toModel(data: WeatherHourDto): WeatherHourModel {
        return WeatherHourModel(
            time = data.time,
            tempC = data.tempC,
            weatherCode = data.weatherCode
        )
    }
}
