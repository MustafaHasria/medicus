package com.mustafahasria.medicus.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustafahasria.medicus.model.medical.dao.ReportDao
import com.mustafahasria.medicus.model.medical.entity.ReportEntity

@Database(entities = [ReportEntity::class], version = 1, exportSchema = false)
abstract class MedicusDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME: String = "medicus_db"
    }

    abstract fun getReportDao(): ReportDao
}