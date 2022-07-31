package com.mustafahasria.medicus.ui.detailsreport

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mustafahasria.medicus.R
import com.mustafahasria.medicus.databinding.FragmentDetailsBinding
import com.mustafahasria.medicus.ui.home.HomeViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    //region ViewModel & Binding
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    ///endregion

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailsBinding.bind(view)
        if (args.report.color == "#E64A19") {
            binding.fragmentDetailsTextViewSymbolCircle.setBackgroundResource(R.drawable.blue_circular_card)
            binding.fragmentDetailsTextViewSymbolCircle.setTextColor("#00BFD4".toColorInt())
            binding.fragmentDetailsTextViewValue.setTextColor("#00BFD4".toColorInt())

        }

        binding.fragmentDetailsTextViewSymbol.text = args.report.symbol
        binding.fragmentDetailsTextViewSymbolCircle.text = args.report.symbol
        binding.fragmentDetailsTextViewDate.text = args.report.date
        binding.fragmentDetailsTextViewValue.text = getString(R.string.result) + args.report.value.toString()
        binding.fragmentDetailsTextViewInfo.text = args.report.info
        binding.fragmentDetailsTextViewInsight.text = args.report.insight


    }
}