package org.sopt.sample.presentation.login.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SignUpViewModel : ViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val mbti = MutableLiveData<String>()

    val isInputValid = MediatorLiveData<Boolean>()

    private var _isSignUpSuccess = MutableLiveData(false)
    val isSignUpSuccess: LiveData<Boolean> get() = _isSignUpSuccess

    init {
        isInputValid.value = false
        isEnabledSignupButton()
    }

    //SignUp btn click 시 실행
    private fun isEnabledSignupButton() {
        isInputValid.apply {
            addSource(id) { value = inputValidCheck() }
            addSource(pw) { value = inputValidCheck() }
        }
    }

    private fun inputValidCheck(): Boolean =
        (id.value?.length in 6..10) && (pw.value?.length in 8..12)
}