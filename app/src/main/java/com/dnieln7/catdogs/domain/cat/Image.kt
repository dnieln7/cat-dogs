package com.dnieln7.catdogs.domain.cat


import com.squareup.moshi.Json

data class Image(
    @Json(name = "height")
    val height: Int? = 0,
    @Json(name = "id")
    val id: String? = "",
    @Json(name = "url")
    val url: String? = "",
    @Json(name = "width")
    val width: Int? = 0
)