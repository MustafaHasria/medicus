package com.mustafahasria.medicus.model.medical.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Report(
    val id: Int,
    val date: String?,
    val info: String?,
    val color: String?,
    val value: Int?,
    val symbol: String?,
    val insight: String?,
    val category: String?,
) : Parcelable