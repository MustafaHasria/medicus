package com.mustafahasria.medicus.repository.medical

import com.mustafahasria.medicus.api.MedicusApi
import com.mustafahasria.medicus.model.medical.dao.ReportDao
import com.mustafahasria.medicus.model.medical.domain.Report
import com.mustafahasria.medicus.model.medical.mapper.ReportDtoMapper
import com.mustafahasria.medicus.model.medical.mapper.ReportEntityMapper
import com.mustafahasria.medicus.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportRepository @Inject constructor(
    private val medicusApi: MedicusApi,
    private val reportDtoMapper: ReportDtoMapper,
    private val reportEntityMapper: ReportEntityMapper,
    private val reportDao: ReportDao,
) {
    suspend fun getReport(): Flow<DataState<List<Report>>> = flow {
        emit(DataState.Loading)
        try {
            val response = medicusApi.getAllReports()
            val res = reportDtoMapper.mapFromModelList(response)
            val entityReports = reportEntityMapper.mapFromDomainList(res)
            if (reportDao.getCounts() == 0) {
                reportDao.insertAll(entityReports)
            } else if (reportDao.getCounts() < response.size) {
                reportDao.deleteAll()
                reportDao.insertAll(entityReports)
            }
            emit(DataState.Success(res))
        } catch (e: Exception) {
            emit(DataState.ExceptionState(e))
        }
    }

    suspend fun getReportOffline(): Flow<DataState<List<Report>>> = flow {
        emit(DataState.Loading)
        try {
            val res = reportDao.getReports()
            emit(DataState.Success(reportEntityMapper.mapFromModelList(res)))
        } catch (e: Exception) {
            emit(DataState.ExceptionState(e))
        }
    }
}
