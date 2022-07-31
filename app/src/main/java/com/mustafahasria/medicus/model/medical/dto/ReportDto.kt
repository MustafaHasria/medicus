package com.mustafahasria.medicus.model.medical.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ReportDto (
    val id: Int,
    val date: String?,
    val info: String?,
    val color: String?,
    val value: Int?,
    val symbol: String?,
    val insight: String?,
    val category: String?,
)