package com.iagora.wingman.auth.otp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.auth.otp.domain.use_case.field_validation_use_case.PhoneNumberFieldValidationUseCase
import com.iagora.wingman.auth.otp.domain.use_case.is_aunthenticated_use_case.IsAuthenticatedUseCase
import com.iagora.wingman.auth.otp.domain.use_case.is_wingman_complete_data_use_case.IsWingmanCompleteDataUseCase
import com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case.SendOTPUseCase
import com.iagora.wingman.auth.otp.presentation.event.InputPhoneNumberDataEvent
import com.iagora.wingman.auth.otp.presentation.event.AuthRequestOTPCodeMessageEvent
import com.iagora.wingman.auth.otp.presentation.event.AuthRequestOTPCodeStatusEvent
import com.iagora.wingman.auth.otp.presentation.state.AuthenticationState
import com.iagora.wingman.auth.otp.presentation.state.InputPhoneNumberState
import com.iagora.wingman.common.presentation.event.FormValidationEvent
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthRequestOTPCodeViewModel @Inject constructor(
    private val sendOTPUseCase: SendOTPUseCase,
    private val isAuthenticatedUseCase: IsAuthenticatedUseCase,
    private val isWingmanCompleteDataUseCase: IsWingmanCompleteDataUseCase,
    private val phoneNumberFieldValidationUseCase: PhoneNumberFieldValidationUseCase,
) : ViewModel() {
    private val _authenticationState = MutableStateFlow(AuthenticationState())
    val authenticationState: StateFlow<AuthenticationState> =
        _authenticationState.asStateFlow()

    private val _isWingmanCompleteDataState = MutableStateFlow(false)
    val isWingmanCompleteDataState: StateFlow<Boolean> = _isWingmanCompleteDataState.asStateFlow()

    private val _inputPhoneNumberState = MutableStateFlow(InputPhoneNumberState())
    val inputPhoneNumberState: StateFlow<InputPhoneNumberState> =
        _inputPhoneNumberState.asStateFlow()

    private val inputPhoneNumberEventChannel = Channel<FormValidationEvent>()
    val inputPhoneNumberEvents = inputPhoneNumberEventChannel.receiveAsFlow()

    private val authRequestOTPCodeStatusEventChannel = Channel<AuthRequestOTPCodeStatusEvent>()
    val authRequestOTPCodeStatusEvents = authRequestOTPCodeStatusEventChannel.receiveAsFlow()

    init {
        isAuthenticated()
        isWingmanCompleteData()
    }

    private fun isWingmanCompleteData() {
        viewModelScope.launch {
            _isWingmanCompleteDataState.value = isWingmanCompleteDataUseCase()
        }
    }

    private fun isAuthenticated() {
        viewModelScope.launch {
            isAuthenticatedUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> _authenticationState.update { it.copy(isError = true) }
                    is Resource.Loading -> _authenticationState.update { it.copy(isLoading = result.isLoading) }
                    is Resource.Success -> {
                        result.data.let { data ->
                            _authenticationState.update {
                                it.copy(
                                    isAuthenticated = data?.isAuthenticatedStatus ?: false
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun onInputFieldEvent(event: InputPhoneNumberDataEvent) {
        when (event) {
            is InputPhoneNumberDataEvent.PhoneNumberFieldChange -> _inputPhoneNumberState.update {
                it.copy(
                    phoneNumber = event.phoneNumber
                )
            }
            InputPhoneNumberDataEvent.Submit -> submit()
            InputPhoneNumberDataEvent.SendRequestOTPCode -> requestOTPCode()
        }
    }

    private fun onAuthRequestOTPCodeMessageEvent(event: AuthRequestOTPCodeMessageEvent) {
        when (event) {
            is AuthRequestOTPCodeMessageEvent.ErrorMessageChange -> {
                _inputPhoneNumberState.update {
                    it.copy(errorMessage = event.errorMessage)
                }
            }
            is AuthRequestOTPCodeMessageEvent.IsLoadingChange -> {
                _inputPhoneNumberState.update {
                    it.copy(isLoading = event.isLoading)
                }
                Timber.i("${event.isLoading}")
            }
        }
    }

    private fun requestOTPCode() {
        viewModelScope.launch {
            sendOTPUseCase(_inputPhoneNumberState.value.phoneNumber).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        onAuthRequestOTPCodeMessageEvent(AuthRequestOTPCodeMessageEvent.IsLoadingChange(
                            true))
                    }
                    is Resource.Error -> {
                        onAuthRequestOTPCodeMessageEvent(AuthRequestOTPCodeMessageEvent.IsLoadingChange(
                            false))
                        onAuthRequestOTPCodeMessageEvent(AuthRequestOTPCodeMessageEvent.ErrorMessageChange(
                            result.message
                                ?: UIText.unknownError()))
                        authRequestOTPCodeStatusEventChannel.send(AuthRequestOTPCodeStatusEvent.Error)
                    }
                    is Resource.Success -> {
                        onAuthRequestOTPCodeMessageEvent(AuthRequestOTPCodeMessageEvent.IsLoadingChange(
                            false))
                        authRequestOTPCodeStatusEventChannel.send(AuthRequestOTPCodeStatusEvent.Success)
                    }
                }
            }
        }
    }

    private fun submit() {
        val phoneNumberResult =
            phoneNumberFieldValidationUseCase.execute(_inputPhoneNumberState.value.phoneNumber)
        val hasError = listOf(phoneNumberResult).any { !it.successful }

        if (hasError) {
            _inputPhoneNumberState.update {
                it.copy(
                    phoneNumberFieldError = phoneNumberResult.errorMessage
                )
            }
            return
        }

        // event to ui is loading status
        onAuthRequestOTPCodeMessageEvent(AuthRequestOTPCodeMessageEvent.IsLoadingChange(
            true))

        // event submit form data success
        viewModelScope.launch {
            inputPhoneNumberEventChannel.send(FormValidationEvent.Success)
        }
    }
}