package org.sopt.sample.presentation.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SignInViewModel : ViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val mbti = MutableLiveData<String>()


    private var _isInputValid = MutableLiveData(false)
    val isInputValid:LiveData<Boolean> get() = _isInputValid

    private var _isSignInSuccess = MutableLiveData(false)
    val isSignInSuccess: LiveData<Boolean> get() = _isSignInSuccess

    //SignUp btn click 시 실행
    fun inputValidCheck() {
        _isInputValid.value =
            !(id.value.isNullOrBlank() || pw.value.isNullOrBlank() || mbti.value.isNullOrBlank())
        _isInputValid.value =
            (id.value?.length  in 6..10) && (pw.value?.length in 8..12)
    }
    fun signIn() {

    }
}