package com.iagora.wingman.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.dashboard.domain.use_case.greeting_use_case.GreetingUseCase
import com.iagora.wingman.dashboard.presentation.state.GreetingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val greetingUseCase: GreetingUseCase) :
    ViewModel() {
    private val _greetingState = MutableStateFlow(GreetingState())
    val greetingState: StateFlow<GreetingState> = _greetingState.asStateFlow()

    init {
        getGreetingMessage()
    }

    private fun getGreetingMessage() {
        viewModelScope.launch {
            greetingUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> _greetingState.update {
                        it.copy(
                            isLoading = false,
                            greeting = "❌"
                        )
                    }
                    is Resource.Loading -> _greetingState.update {
                        it.copy(
                            isLoading = true,
                            greeting = "⏰"
                        )
                    }
                    is Resource.Success -> _greetingState.update {
                        it.copy(
                            isLoading = false,
                            greeting = result.data?.greeting ?: ""
                        )
                    }
                }
            }
        }
    }
}