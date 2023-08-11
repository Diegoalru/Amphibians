package com.darssolutions.amphibians.domain

import com.squareup.moshi.Json

data class Amphibian(
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String,
    @Json(name = "description") val description: String,
)