package org.sopt.sample.presentation.login.signup

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sopt.sample.data.auth.model.SignUpRequest
import org.sopt.sample.data.auth.repository.AuthRepository


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
            addSource(id) { value = inputValidCheck() }
            addSource(pw) { value = inputValidCheck() }
        }
    }

    private fun inputValidCheck(): Boolean =
        (id.value?.length in 6..10) && (pw.value?.length in 8..12)
}