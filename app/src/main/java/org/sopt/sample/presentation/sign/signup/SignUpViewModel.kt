package org.sopt.sample.presentation.sign.signup

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.model.SignUpRequest
import org.sopt.sample.domain.AuthRepository
import org.sopt.sample.util.addSourceList
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    companion object {
        //id - 6~10글자 영문 숫자 포함
        //pw - 6~12글자 영문,숫자,특수문자 포함
        private val REGEX_ID_PATTERN = Pattern.compile("^(?=.*[a-zA-Z]+)(?=.*[0-9]+).{6,10}$")
        private val REGEX_PW_PATTERN =
            Pattern.compile("^(?=.*[a-zA-Z]+)(?=.*[0-9]+)(?=.*[!@#$%^&*()~`<>?:']+).{6,12}$")
    }


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
            addSourceList(name, id, pw) { checkInputValid() }
        }
    }

    private fun checkInputValid(): Boolean = isNameValid && isIdValid && isPwValid

    val isNameValid: Boolean
        get() = name.value?.let { name.value!!.length >= 2 } == true
    val isIdValid: Boolean
        get() = id.value?.let { REGEX_ID_PATTERN.matcher(it).matches() } == true
    val isPwValid: Boolean
        get() = pw.value?.let { REGEX_PW_PATTERN.matcher(it).matches() } == true

    private fun signUpRequest(): SignUpRequest =
        SignUpRequest(id.value.toString(), name.value.toString(), pw.value.toString())


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
        postSignUp(signUpRequest())
    }
}