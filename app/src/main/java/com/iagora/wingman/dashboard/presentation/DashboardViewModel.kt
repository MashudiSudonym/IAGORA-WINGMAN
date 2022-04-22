package com.iagora.wingman.dashboard.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.auth.credential.domain.use_case.is_aunthenticated_use_case.IsAuthenticatedUseCase
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.dashboard.presentation.state.DashboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val isAuthenticatedUseCase: IsAuthenticatedUseCase) :
    ViewModel() {
    var state by mutableStateOf(DashboardState())

    init {
        isAuthenticated()
    }

    private fun isAuthenticated() {
        viewModelScope.launch {
            isAuthenticatedUseCase().collect { result ->
                state = when (result) {
                    is Resource.Error -> state.copy(isError = true)
                    is Resource.Loading -> state.copy(isLoading = result.isLoading)
                    is Resource.Success -> {
                        result.data.let {
                            state.copy(isAuthenticated = it ?: false)
                        }
                    }
                }
            }
        }
    }
}