package com.mustafahasria.medicus.ui

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.mustafahasria.medicus.R
import com.mustafahasria.medicus.databinding.ActivityMainBinding
import com.mustafahasria.medicus.util.ConnectionStateViewModel
import com.mustafahasria.medicus.util.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //region ViewModel & Binding
//    private val connectionStateViewModel: ConnectionStateViewModel by viewModels()
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!


    ///endregion


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        NetworkConnection(this).observe(this) {
//            connectionStateViewModel.isDissconnect.value = !it.state
//            binding.root.isVisible = it.state
//            if (!it.state) {
//                findNavController(R.id.fragmentMainNavHost).navigate(R.id.action_global_network)
//            }
//        }

    }
}