package com.mustafahasria.medicus.model.medical.dao

import androidx.room.*
import com.mustafahasria.medicus.model.medical.entity.ReportEntity

@Dao
interface ReportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reportEntity: ReportEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categoryEntities: List<ReportEntity>)

    @Delete
    suspend fun delete(reportEntity: ReportEntity)

    @Query("delete from reports_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(reportEntity: ReportEntity)

    @Query("Select * From reports_table")
    suspend fun getReports(): List<ReportEntity>

    @Query("Select count(report_id) From reports_table")
    suspend fun getCounts(): Int
}