package com.dnieln7.catdogs.domain.cat


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Weight(
    @Json(name = "imperial")
    val imperial: String,
    @Json(name = "metric")
    val metric: String?
)