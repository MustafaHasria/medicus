package com.mustafahasria.medicus.model.medical.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reports_table")
data class ReportEntity(

    @ColumnInfo(name = "report_id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "report_date")
    val date: String?,

    @ColumnInfo(name = "report_info")
    val info: String?,

    @ColumnInfo(name = "report_color")
    val color: String?,

    @ColumnInfo(name = "report_value")
    val value: Int?,

    @ColumnInfo(name = "report_symbol")
    val symbol: String?,

    @ColumnInfo(name = "report_insight")
    val insight: String?,

    @ColumnInfo(name = "report_category")
    val category: String?,
)