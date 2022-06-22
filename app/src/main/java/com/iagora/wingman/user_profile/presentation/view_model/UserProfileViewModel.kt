package com.iagora.wingman.user_profile.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import com.iagora.wingman.user_profile.domain.use_case.wingman_logout_use_case.WingmanLogoutUseCase
import com.iagora.wingman.user_profile.presentation.event.UserProfileEvent
import com.iagora.wingman.user_profile.presentation.state.LogoutState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(private val wingmanLogoutUseCase: WingmanLogoutUseCase) :
    ViewModel() {

    private val _logoutState = MutableStateFlow(LogoutState())
    val logoutState: StateFlow<LogoutState> = _logoutState.asStateFlow()

    private val userProfileErrorChannel = Channel<UIText>()
    val userProfileErrors = userProfileErrorChannel.receiveAsFlow()

    fun onEvent(userProfileEvent: UserProfileEvent) {
        when (userProfileEvent) {
            is UserProfileEvent.Logout -> {
                logoutMenu()
            }
        }
    }

    private fun logoutMenu() {
        viewModelScope.launch {
            wingmanLogoutUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _logoutState.update {
                            it.copy(
                                isLoading = false,
                                isError = true
                            )
                        }
                        userProfileErrorChannel.send(result.message ?: UIText.unknownError())
                    }
                    is Resource.Loading -> _logoutState.update { it.copy(isLoading = true) }
                    is Resource.Success -> _logoutState.update {
                        it.copy(
                            isLoading = false,
                            isError = false,
                            logoutSuccess = result.data ?: false
                        )
                    }
                }
            }
        }
    }
}