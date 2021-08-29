package com.stech.data.api.model

import com.squareup.moshi.Json

class AttatchmentsApiResponse(val items: List<Attatchments>)
data class Attatchments(
    @field:Json(name = "id")
    val id : String,
    @field:Json(name = "type")
    val type : String,
    @field:Json(name = "url")
    val url : String,
    @field:Json(name = "name")
    val name : String,
)
