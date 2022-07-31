package com.mustafahasria.medicus.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectionStateViewModel @Inject constructor() : ViewModel() {
    var isDissconnect = MutableLiveData<Boolean>()
}