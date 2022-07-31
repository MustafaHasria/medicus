package com.mustafahasria.medicus.di

import android.content.Context
import androidx.room.Room
import com.mustafahasria.medicus.db.database.MedicusDatabase
import com.mustafahasria.medicus.model.medical.dao.ReportDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesMedicusDb(@ApplicationContext context: Context): MedicusDatabase {
        return Room.databaseBuilder(
            context,
            MedicusDatabase::class.java,
            MedicusDatabase.DATABASE_NAME
        )
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun providesReportDao(medicusDatabase: MedicusDatabase): ReportDao {
        return medicusDatabase.getReportDao()
    }


}