package com.iagora.wingman.auth.ssid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.auth.ssid.domain.use_case.get_wingman_ssid_use_case.GetWingmanSSIDUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WingmanSSIDViewModel @Inject constructor(private val getWingmanSSIDUseCase: GetWingmanSSIDUseCase) :
    ViewModel() {

    init {
        // initialize in first run
        getWingmanSSID()

        Timber.d("VM RUN!")
    }

    private fun getWingmanSSID() {
        viewModelScope.launch(Dispatchers.IO) {
            getWingmanSSIDUseCase()
        }
    }
}