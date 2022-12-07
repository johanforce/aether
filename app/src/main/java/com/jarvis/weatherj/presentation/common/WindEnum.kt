package com.jarvis.weatherj.presentation.common

import com.jarvis.weatherj.MainApplication
import com.jarvis.weatherj.R

enum class WindEnum(val index: Int, val wind: String, val Data: String) {
    N(0, "N", MainApplication.applicationContext().getString(R.string.north)),
    NNE(1, "NNE", MainApplication.applicationContext().getString(R.string.north_north_east)),
    NE(2, "NE", MainApplication.applicationContext().getString(R.string.north_east)),
    ENE(3, "ENE", MainApplication.applicationContext().getString(R.string.east_north_east)),
    E(4, "E", MainApplication.applicationContext().getString(R.string.east)),
    ESE(5, "ESE", MainApplication.applicationContext().getString(R.string.east_south_east)),
    SE(6, "SE", MainApplication.applicationContext().getString(R.string.south_east)),
    SSE(7, "SSE", MainApplication.applicationContext().getString(R.string.south_south_east)),
    S(8, "S", MainApplication.applicationContext().getString(R.string.south)),
    SSW(9, "SSW", MainApplication.applicationContext().getString(R.string.south_south_west)),
    SW(10, "SW", MainApplication.applicationContext().getString(R.string.south_west)),
    WSW(11, "WSW", MainApplication.applicationContext().getString(R.string.west_south_west)),
    W(12, "W", MainApplication.applicationContext().getString(R.string.west)),
    WNW(13, "WNW", MainApplication.applicationContext().getString(R.string.west_north_west)),
    NW(14, "NW", MainApplication.applicationContext().getString(R.string.north_west)),
    NNW(15, "NNW", MainApplication.applicationContext().getString(R.string.north_north_west))
}