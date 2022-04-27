package com.iagora.wingman.customer_care.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.customer_care.domain.use_case.get_access_token_use_case.GetAccessTokenUseCase
import com.iagora.wingman.customer_care.presentation.state.AccessTokenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerCareViewModel @Inject constructor(private val getAccessTokenUseCase: GetAccessTokenUseCase) :
    ViewModel() {
    private val _accessTokenState = MutableStateFlow(AccessTokenState())
    val accessTokenState: StateFlow<AccessTokenState> = _accessTokenState.asStateFlow()

    init {
        getAccessToken()
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            getAccessTokenUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> _accessTokenState.update {
                        it.copy(
                            accessToken = "BLANK",
                            isLoading = false,
                            isError = true
                        )
                    }
                    is Resource.Loading -> _accessTokenState.update { it.copy(isLoading = true) }
                    is Resource.Success -> _accessTokenState.update {
                        it.copy(
                            accessToken = result.data?.accessToken ?: "Error null",
                            isLoading = false,
                            isError = false
                        )
                    }
                }
            }
        }
    }
}