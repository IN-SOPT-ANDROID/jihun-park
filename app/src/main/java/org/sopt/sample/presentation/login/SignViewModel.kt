package org.sopt.sample.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.presentation.model.UserInfo
import org.sopt.sample.util.Event

enum class SignUpFail {
    ID_FAIL, PW_FAIL, MBTI_FAIL
}

class SignViewModel : ViewModel() {
    private val mbtiList: List<String> = listOf(
        "ENFJ", "ENTJ", "ENFP", "ENTP",
        "ESFP", "ESFJ", "ESTP", "ESTJ",
        "INFP", "INFJ", "INTP", "ISTP",
        "ISFP", "ISFJ", "ISTJ", "INTJ"
    )

    //asStateFlow -> Represents this mutable state flow as a read-only state flow.
    val id = MutableStateFlow<String>("")
    val pw = MutableStateFlow<String>("")
    val mbti = MutableStateFlow<String>("")
    var userInput: UserInfo? = null
    //Presentation layer - VIEW,VIEWMODEL
    //Domain Layer -
    //Data Layer - DB, ROOM
    lateinit var failReason: Enum<SignUpFail>
    private var _isSignUpInputValid = MutableStateFlow<Event<Boolean>>(Event(false))
    val isSignUpInputValid: StateFlow<Event<Boolean>> get() = _isSignUpInputValid

    private var _isSignInSuccess = MutableStateFlow<Event<Boolean>>(Event(false))
    val isSignInSuccess: StateFlow<Event<Boolean>> get() = _isSignInSuccess


    //SignUp btn click 시 실행
    fun inputValidCheck() {
        _isSignUpInputValid.value =
            Event(!(id.value.isBlank() || pw.value.isBlank() || mbti.value.isBlank()))
        _isSignUpInputValid.value =
            Event((id.value.length in 6..10) && (pw.value.length in 8..12) && mbti.value.uppercase() in mbtiList)

        if (id.value.length in 6..10) failReason = SignUpFail.ID_FAIL
        else if (pw.value.length in 8..12) failReason = SignUpFail.PW_FAIL
        else if (mbti.value.uppercase() in mbtiList) failReason = SignUpFail.MBTI_FAIL
    }

    fun signIn() {
        (id.value == userInput?.id && pw.value == userInput?.pw).let { isValid ->
            if (isValid && userInput != null) ApplicationClass.getInstance().getUserManager().setUserInfo(userInput!!)
            _isSignInSuccess.value = Event(isValid)
        }
    }
    //SignInActivity의 setResultSignUp에서 userInfo에 결과값을 집어넣고 viewModel.setUserInfo를 호출
    //viewModel.setUserInfo에서는 SignUp의 결과값을 this.userInput에 전달한 셈.
    //viewModel의 signIn()에서는 signUp에서의 결과를 dataStore에 set하고, _isSignInSuccess.value를 설정
    //SignInActivity에서는 isSignInSuccess를 observe하고 있다가, 위 과정으로 변동되면,moveToHome()호출
    fun setUserInfo(userInput: UserInfo) {
        this.userInput = userInput
    }
}