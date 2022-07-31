package com.mustafahasria.medicus.ui.home

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mustafahasria.medicus.R
import com.mustafahasria.medicus.databinding.FragmentHomeBinding
import com.mustafahasria.medicus.model.medical.domain.Report
import com.mustafahasria.medicus.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    //region ViewModel & Binding

    private val viewModel: HomeViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var reportAdapter: ReportAdapter
    private var reportsList = ArrayList<Report>()

    ///endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        binding.homeSwipeRefresh.setOnRefreshListener {
        }
        initRecyclerAndAdapter()
        if (viewModel.reportList.value == null) {
            if (!isNetworkConnected()) {
                getReportsOffline()
            } else {
                getReports()
            }
        } else {
            reportsList = (viewModel.reportList.value as ArrayList<Report>?)!!
            reportAdapter.setData(reportsList)
        }
    }

    private fun getReportsOffline() {
        observeDataState(0)
        viewModel.getReport(ReportStateEvent.GetReportsOffline)
    }

    private fun getReports() {
        observeDataState(1)
        viewModel.getReport(ReportStateEvent.GetReports)
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = activity?.getSystemService(ConnectivityManager::class.java)
        return connectivityManager?.activeNetworkInfo?.isConnected ?: false
    }

    private fun observeDataState(flag: Int) {
        viewModel.reportDataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.homeProgressBar.visibility = View.VISIBLE
                    binding.homeRecyclerViewReports.visibility = View.GONE
                    binding.homeImageViewNoConnection.visibility = View.GONE
                }
                is DataState.Success -> {
                    if (it.data.isEmpty()) {
                        binding.homeImageViewNoConnection.visibility = View.VISIBLE
                        Toast.makeText(
                            context,
                            "You have to run the app at least with internet to cash",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        binding.homeImageViewNoConnection.visibility = View.GONE
                        binding.homeProgressBar.visibility = View.GONE
                        binding.homeRecyclerViewReports.visibility = View.VISIBLE
                        for (report in it.data) {
                            if (report.date != null)
                                reportsList.add(report)
                        }
                        viewModel.reportList.value = reportsList
                        reportAdapter.setData(reportsList)
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    }
                    viewModel.getReport(ReportStateEvent.None)
                }
//                is DataState.Failure -> {
//                }
                is DataState.ExceptionState -> {
                    binding.homeRecyclerViewReports.visibility = View.GONE
                    binding.homeProgressBar.visibility = View.GONE
                    binding.homeImageViewNoConnection.visibility = View.GONE
                    viewModel.getReport(ReportStateEvent.None)
                }
            }
        }
    }

    private fun initRecyclerAndAdapter() {
        reportAdapter = ReportAdapter(
            reportsList,
        ) { report, position -> clickListener(report, position) }
        binding.homeRecyclerViewReports.apply {
            setHasFixedSize(true)
            adapter = reportAdapter
        }
    }

    private fun clickListener(report: Report, position: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(report)
        findNavController().navigate(action)
    }


}


