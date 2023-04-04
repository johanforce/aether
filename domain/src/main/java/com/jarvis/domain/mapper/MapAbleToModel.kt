package com.jarvis.domain.mapper

interface MapAbleToModel<Model> {
    fun toModel(): Model
}
