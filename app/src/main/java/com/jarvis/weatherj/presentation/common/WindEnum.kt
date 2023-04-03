package com.jarvis.weatherj.presentation.common

import com.jarvis.weatherj.R

enum class WindEnum(val index: Int, val wind: String, val data: Int) {
    N(0, "N", R.string.north),
    NNE(1, "NNE", R.string.north_north_east),
    NE(2, "NE",R.string.north_east),
    ENE(3, "ENE", R.string.east_north_east),
    E(4, "E",R.string.east),
    ESE(5, "ESE",R.string.east_south_east),
    SE(6, "SE", R.string.south_east),
    SSE(7, "SSE", R.string.south_south_east),
    S(8, "S", R.string.south),
    SSW(9, "SSW", R.string.south_south_west),
    SW(10, "SW", R.string.south_west),
    WSW(11, "WSW", R.string.west_south_west),
    W(12, "W", R.string.west),
    WNW(13, "WNW", R.string.west_north_west),
    NW(14, "NW", R.string.north_west),
    NNW(15, "NNW",R.string.north_north_west)
}
