package org.sopt.sample.presentation.login.signup

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sopt.sample.data.auth.model.SignUpRequest
import org.sopt.sample.data.auth.repository.AuthRepository
import org.sopt.sample.util.addSourceList


class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val isInputValid = MediatorLiveData<Boolean>()

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean>
        get() = _signUpSuccess

    init {
        isInputValid.value = false
        isEnabledSignupButton()
    }

    private fun isEnabledSignupButton() {
        isInputValid.apply {
            addSourceList(name,email,pw){inputValidCheck()}
        }
    }

    private fun inputValidCheck(): Boolean = isNameValid() && isEmailValid() && isPwValid()
    fun isNameValid(): Boolean = !name.value.isNullOrBlank()
    fun isEmailValid(): Boolean = email.value?.contains('@') == true
    fun isPwValid(): Boolean = pw.value?.length in 8..12
    private fun getUser(): SignUpRequest {
        return SignUpRequest(email.value.toString(), name.value.toString(), pw.value.toString())
    }

    private fun postSignUp(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                authRepository.signUp(signUpRequest)
            }.onSuccess {
                _signUpSuccess.value = it.status == 201
            }.onFailure {
                _signUpSuccess.value = false
            }
        }
    }

    fun signUp() {
        postSignUp(getUser())
    }
}