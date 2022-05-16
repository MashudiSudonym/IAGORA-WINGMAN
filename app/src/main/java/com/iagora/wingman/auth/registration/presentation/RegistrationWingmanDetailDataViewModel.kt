package com.iagora.wingman.auth.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.AddressFieldValidationUseCase
import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.CityFieldValidationUseCase
import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.EmailFieldValidationUseCase
import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.NameFieldValidationUseCase
import com.iagora.wingman.common.presentation.event.FormValidationEvent
import com.iagora.wingman.auth.registration.presentation.event.WingmanDetailDataEvent
import com.iagora.wingman.auth.registration.presentation.state.RegistrationWingmanDetailDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationWingmanDetailDataViewModel @Inject constructor(
    private val nameFieldValidationUseCase: NameFieldValidationUseCase,
    private val emailFieldValidationUseCase: EmailFieldValidationUseCase,
    private val addressFieldValidationUseCase: AddressFieldValidationUseCase,
    private val cityFieldValidationUseCase: CityFieldValidationUseCase,
) : ViewModel() {
    private val _registrationWingmanDetailDataState =
        MutableStateFlow(RegistrationWingmanDetailDataState())
    val registrationWingmanDetailDataState: StateFlow<RegistrationWingmanDetailDataState> =
        _registrationWingmanDetailDataState.asStateFlow()

    private val registrationWingmanDetailDataEventChannel = Channel<FormValidationEvent>()
    val registrationWingmanDetailDataEvents =
        registrationWingmanDetailDataEventChannel.receiveAsFlow()

    fun onEvent(event: WingmanDetailDataEvent) {
        when (event) {
            is WingmanDetailDataEvent.AddressFieldChange -> _registrationWingmanDetailDataState.update {
                it.copy(
                    address = event.address
                )
            }
            is WingmanDetailDataEvent.CityFieldChange -> _registrationWingmanDetailDataState.update {
                it.copy(
                    city = event.city
                )
            }
            is WingmanDetailDataEvent.EmailFieldChange -> _registrationWingmanDetailDataState.update {
                it.copy(
                    email = event.email
                )
            }
            is WingmanDetailDataEvent.NameFieldChange -> _registrationWingmanDetailDataState.update {
                it.copy(
                    name = event.name
                )
            }
            WingmanDetailDataEvent.Submit -> submitData()
        }
    }

    private fun submitData() {
        val emailResult =
            emailFieldValidationUseCase.execute(_registrationWingmanDetailDataState.value.email)
        val nameResult =
            nameFieldValidationUseCase.execute(_registrationWingmanDetailDataState.value.name)
        val addressResult =
            addressFieldValidationUseCase.execute(_registrationWingmanDetailDataState.value.address)
        val cityResult =
            cityFieldValidationUseCase.execute(_registrationWingmanDetailDataState.value.city)
        val hasError = listOf(
            emailResult, nameResult, addressResult, cityResult
        ).any { !it.successful }

        if (hasError) {
            _registrationWingmanDetailDataState.update {
                it.copy(
                    nameFieldError = nameResult.errorMessage,
                    emailFieldError = emailResult.errorMessage,
                    addressFieldError = addressResult.errorMessage,
                    cityFieldError = cityResult.errorMessage
                )
            }
            return
        }

        viewModelScope.launch {
            registrationWingmanDetailDataEventChannel.send(FormValidationEvent.Success)
        }
    }
}