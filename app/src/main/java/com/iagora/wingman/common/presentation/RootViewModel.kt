package com.iagora.wingman.common.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.data_store.domain.use_case.is_aunthenticated_use_case.IsAuthenticatedUseCase
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.auth.otp.presentation.state.AuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(private val isAuthenticatedUseCase: IsAuthenticatedUseCase) :
    ViewModel() {
    private val _rootAuthenticationState = MutableStateFlow(AuthenticationState())
    val rootAuthenticationState: StateFlow<AuthenticationState> =
        _rootAuthenticationState.asStateFlow()

    init {
        isAuthenticated()
    }

    private fun isAuthenticated() {
        viewModelScope.launch {
            isAuthenticatedUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> _rootAuthenticationState.update { it.copy(isError = true) }
                    is Resource.Loading -> _rootAuthenticationState.update { it.copy(isLoading = result.isLoading) }
                    is Resource.Success -> {
                        result.data.let { data ->
                            _rootAuthenticationState.update {
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