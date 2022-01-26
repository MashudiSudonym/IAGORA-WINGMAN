package com.iagora.wingman.app.auth.presentation.login

import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentLoginBinding
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectLatestWhenStarted
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.*
import com.iagora.wingman.core.util.AuthError
import okhttp3.internal.format
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginFragment :
    BaseFragment<FragmentLoginBinding>
        (R.layout.fragment_login, { FragmentLoginBinding.bind(it) }) {


    private val viewModel: LoginViewModel by viewModel()


    override fun setView() {
        Loader.handleLoading(requireContext())

        requestOTP()
        requestLogin()
        observerEventLogin()
    }

    private fun requestOTP() {
        binding.incSetPhone.apply {
            incBtnLogin.btnAction.setOnClickListener { sendPhoneNumber() }
            tlPhone.editText?.performSendAction { sendPhoneNumber() }
        }
    }

    private fun sendPhoneNumber() {
        val phoneNumber = binding.incSetPhone.tlPhone.editText?.text.toString()
        viewModel.setPhoneNumber(phoneNumber)
    }

    private fun requestLogin() {
        binding.incSetOtp.otpView.setOtpCompletionListener(viewModel::login)
    }

    private fun observerEventLogin() {
        viewModel.phoneNumberError.collectLatestWhenStarted(viewLifecycleOwner) {
            binding.incSetPhone.tlPhone.error = when (it) {
                is AuthError.FieldEmpty -> resources.getString(R.string.error_field_empty)
                is AuthError.InvalidPhoneNumber -> resources.getString(R.string.error_invalid_phone_number)
                else -> ""
            }
        }

        viewModel.phoneNumberCompleted.collectLatestWhenStarted(viewLifecycleOwner) {
            binding.incSetOtp.tvDesc.text = format(resources.getString(R.string.otp_set_desc), it)
        }

        viewModel.loginState.collectLatestWhenStarted(viewLifecycleOwner) { isLoading ->
            if (isLoading) Loader.progressDialog?.show()
            else Loader.progressDialog?.dismiss()
        }
        viewModel.eventFlow.collectWhenStarted(viewLifecycleOwner) { event ->

            when (event) {
                is UiEvent.CreateMessage -> {
                    Snackbar.make(
                        binding.root,
                        event.uiText.asString(requireContext()),
                        Snackbar.LENGTH_SHORT
                    ).apply {
                        customPrimaryColor(this.context)
                    }.show()
                    Timber.e("EVENT ${event.uiText}")
                }
                is LoginEvent.OnGetOtp -> {
                    with(binding) {
                        incSetPhone.root.hide()
                        incSetOtp.root.show()
                    }
                }

                is LoginEvent.OnLogin -> {

                }
            }
        }
    }


}