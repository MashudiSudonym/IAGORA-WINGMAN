package com.iagora.wingman.auth.registration.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.auth.registration.domain.use_case.field_validation_use_case.*
import com.iagora.wingman.auth.registration.presentation.event.WingmanDocumentDataEvent
import com.iagora.wingman.auth.registration.presentation.state.RegistrationWingmanDocumentDataState
import com.iagora.wingman.common.presentation.event.FormValidationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationWingmanDocumentDataViewModel @Inject constructor(
    private val bankAccountNumberFieldValidationUseCase: BankAccountNumberFieldValidationUseCase,
    private val bankAccountUserNameFieldValidationUseCase: BankAccountUserNameFieldValidationUseCase,
    private val bankNameFieldValidationUseCase: BankNameFieldValidationUseCase,
    private val userIdCardImageValidationUseCase: UserIdCardImageValidationUseCase,
    private val userPoliceAgreementLetterImageValidationUseCase: UserPoliceAgreementLetterImageValidationUseCase
) : ViewModel() {
    private val _registrationWingmanDocumentDataState =
        MutableStateFlow(RegistrationWingmanDocumentDataState())
    val registrationWingmanDocumentDataState: StateFlow<RegistrationWingmanDocumentDataState> =
        _registrationWingmanDocumentDataState.asStateFlow()

    private val registrationWingmanDocumentDataEventChannel = Channel<FormValidationEvent>()
    val registrationWingmanDocumentDateEvents =
        registrationWingmanDocumentDataEventChannel.receiveAsFlow()

    fun onEvent(event: WingmanDocumentDataEvent) {
        when (event) {
            is WingmanDocumentDataEvent.BankAccountNumberFieldChange -> _registrationWingmanDocumentDataState.update {
                it.copy(bankAccountNumber = event.bankAccountNumber)
            }
            is WingmanDocumentDataEvent.BankAccountUserNameFieldChange -> _registrationWingmanDocumentDataState.update {
                it.copy(bankAccountUserName = event.bankAccountUserName)
            }
            is WingmanDocumentDataEvent.BankNameFieldChange -> _registrationWingmanDocumentDataState.update {
                it.copy(bankName = event.bankName)
            }
            is WingmanDocumentDataEvent.UserIdCardImage -> _registrationWingmanDocumentDataState.update {
                it.copy(userIdCardImage = event.userIdCardImage)
            }
            is WingmanDocumentDataEvent.UserPoliceAgreementLetterImage -> _registrationWingmanDocumentDataState.update {
                it.copy(userPoliceAgreementLetterImage = event.userPoliceAgreementLetterImage)
            }
            WingmanDocumentDataEvent.Submit -> submitData()
        }
    }

    private fun submitData() {
        val bankAccountNumberResult =
            bankAccountNumberFieldValidationUseCase.execute(_registrationWingmanDocumentDataState.value.bankAccountNumber)
        val bankAccountUserNameResult =
            bankAccountUserNameFieldValidationUseCase.execute(_registrationWingmanDocumentDataState.value.bankAccountUserName)
        val bankNameResult =
            bankNameFieldValidationUseCase.execute(_registrationWingmanDocumentDataState.value.bankName)
        val userIdCardImageResult =
            userIdCardImageValidationUseCase.execute(_registrationWingmanDocumentDataState.value.userIdCardImage)
        val userPoliceAgreementLetterImageResult =
            userPoliceAgreementLetterImageValidationUseCase.execute(
                _registrationWingmanDocumentDataState.value.userPoliceAgreementLetterImage
            )
        val hasError = listOf(
            bankAccountNumberResult,
            bankAccountUserNameResult,
            bankNameResult,
            userIdCardImageResult,
            userPoliceAgreementLetterImageResult
        ).any { !it.successful }

        if (hasError) {
            _registrationWingmanDocumentDataState.update {
                it.copy(
                    bankAccountNumberFieldError = bankAccountNumberResult.errorMessage,
                    bankAccountUserNameFieldError = bankAccountUserNameResult.errorMessage,
                    bankNameFieldError = bankNameResult.errorMessage,
                    userIdCardImageFieldError = userIdCardImageResult.errorMessage,
                    userPoliceAgreementLetterImageFieldError = userPoliceAgreementLetterImageResult.errorMessage
                )
            }
            return
        }

        viewModelScope.launch {
            registrationWingmanDocumentDataEventChannel.send(FormValidationEvent.Success)
        }
    }
}