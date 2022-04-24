package com.iagora.wingman.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.data_store.domain.use_case.is_aunthenticated_use_case.IsAuthenticatedUseCase
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.dashboard.presentation.state.DashboardAuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val isAuthenticatedUseCase: IsAuthenticatedUseCase) :
    ViewModel() {
    private val _dashboardAuthenticationState = MutableStateFlow(DashboardAuthenticationState())
    val dashboardAuthenticationState: StateFlow<DashboardAuthenticationState> =
        _dashboardAuthenticationState.asStateFlow()

    init {
        isAuthenticated()
    }

    private fun isAuthenticated() {
        viewModelScope.launch {
            isAuthenticatedUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> _dashboardAuthenticationState.update { it.copy(isError = true) }
                    is Resource.Loading -> _dashboardAuthenticationState.update { it.copy(isLoading = result.isLoading) }
                    is Resource.Success -> {
                        result.data.let { data ->
                            _dashboardAuthenticationState.update {
                                it.copy(
                                    isAuthenticated = data ?: false
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}