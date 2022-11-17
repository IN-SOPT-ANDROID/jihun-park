package org.sopt.sample.presentation.login.signup

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SignUpViewModel : ViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val isInputValid = MediatorLiveData<Boolean>()

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