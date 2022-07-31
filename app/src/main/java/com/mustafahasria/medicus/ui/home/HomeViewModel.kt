package com.mustafahasria.medicus.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafahasria.medicus.model.medical.domain.Report
import com.mustafahasria.medicus.repository.medical.ReportRepository
import com.mustafahasria.medicus.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reportRepository: ReportRepository
) : ViewModel() {
    var reportList: MutableLiveData<List<Report>> = MutableLiveData()
    var reportDataState : MutableLiveData<DataState<List<Report>>> = MutableLiveData()
    fun getReport(reportStateEvent: ReportStateEvent){
        viewModelScope.launch {
            when (reportStateEvent) {
                is ReportStateEvent.GetReports -> {
                    reportRepository.getReport().onEach {
                        reportDataState.value = it
                    }.launchIn(viewModelScope)
                    }
                is ReportStateEvent.GetReportsOffline -> {
                    reportRepository.getReportOffline().onEach {
                        reportDataState.value = it
                    }.launchIn(viewModelScope)
                }

                is ReportStateEvent.None -> {
                    reportDataState = MutableLiveData()
                }

            }
        }
    }

}
sealed class ReportStateEvent {
    object GetReports : ReportStateEvent()
    object GetReportsOffline : ReportStateEvent()
    object None : ReportStateEvent()
}
