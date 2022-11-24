package org.sopt.sample.presentation.login.signup

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sopt.sample.data.auth.model.SignUpRequest
import org.sopt.sample.data.auth.repository.AuthRepository
import org.sopt.sample.util.addSourceList
import java.util.regex.Pattern


class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    //id - 6~10글자 영문 숫자 포함
    //pw - 6~12글자 영문,숫자,특수문자 포함
    private val idRegex = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+).{6,10}$"
    private val pwRegex = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+)(?=.*[!@#$%^&*()~`<>?:']+).{6,12}$"
    private val idMatcher: Pattern = Pattern.compile(idRegex)
    private val pwMatcher: Pattern = Pattern.compile(pwRegex)

    val name = MutableLiveData<String>()
    val id = MutableLiveData<String>()
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
            addSourceList(name, id, pw) { inputValidCheck() }
        }
    }

    private fun inputValidCheck(): Boolean = isNameValid && isIdValid && isPwValid
    val isNameValid: Boolean
        get() = name.value?.let{ name.value!!.length >=2}==true
    val isIdValid: Boolean
        get() = id.value?.let { idMatcher.matcher(it).matches() } == true
    val isPwValid: Boolean
        get() = pw.value?.let { pwMatcher.matcher(it).matches() } == true

    private fun request(): SignUpRequest {
        return SignUpRequest(id.value.toString(), name.value.toString(), pw.value.toString())
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
        postSignUp(request())
    }
}