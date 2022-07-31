package com.mustafahasria.medicus.api

import com.mustafahasria.medicus.model.medical.dto.ReportDto
import retrofit2.http.GET

interface MedicusApi {

    companion object {
        const val BASE_URL = "https://retoolapi.dev/hZZ5j8/"
    }

    @GET("biomarkers")
    suspend fun getAllReports(): List<ReportDto>

}