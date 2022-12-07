package org.sopt.sample.presentation.sign.signin

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.model.SignInRequest
import org.sopt.sample.domain.AuthRepository
import org.sopt.sample.util.addSourceList
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val email = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val isInputValid = MediatorLiveData<Boolean>()
    private val _signInSuccess = MutableLiveData<Boolean>()
    val signInSuccess: LiveData<Boolean>
        get() = _signInSuccess
    private val request: SignInRequest
        get() = SignInRequest(email.value.toString(), pw.value.toString())

    init {
        isInputValid.value = false
        isEnabledSigninButton()
    }

    private fun isEnabledSigninButton() {
        isInputValid.apply {
            addSourceList(email, pw) { inputValidCheck() }

        }
    }

    private fun inputValidCheck(): Boolean = isEmailValid() && isPwValid()

    private fun isEmailValid(): Boolean = !email.value.isNullOrBlank()
    private fun isPwValid(): Boolean = !pw.value.isNullOrBlank()



    //viewModel Factory에게 매개변수를 통해 받은 authRepository로 통신 함수 호출
    private fun postSignIn(signInRequest: SignInRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                authRepository.signIn(signInRequest)
            }.onSuccess {
                _signInSuccess.value = it.status == 200
            }.onFailure {
                _signInSuccess.value = false
                pw.value = ""                   //실패 시 pw 입력 초기화
            }
        }
    }
    fun signIn() {
        postSignIn(request)
    }

}