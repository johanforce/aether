package com.jarvis.weatherj.domain.mapper

interface MapAbleToModel<Model> {
    fun toModel(): Model
}
