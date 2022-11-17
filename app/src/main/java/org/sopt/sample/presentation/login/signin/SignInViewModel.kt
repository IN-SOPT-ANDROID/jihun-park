package org.sopt.sample.presentation.login.signin

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SignInViewModel : ViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val isInputValid = MediatorLiveData<Boolean>()

    init {
        isInputValid.value = false
        isEnabledSigninButton()
    }

    private fun isEnabledSigninButton() {
        isInputValid.apply {
            addSource(id) { value = inputValidCheck() }
            addSource(pw) { value = inputValidCheck() }
        }
    }

    private fun inputValidCheck(): Boolean = id.value.isNullOrBlank() || pw.value.isNullOrBlank()
}