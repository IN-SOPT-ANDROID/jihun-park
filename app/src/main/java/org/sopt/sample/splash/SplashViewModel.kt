package org.sopt.sample.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.util.Event

class SplashViewModel : ViewModel() {
    //backing property를 이용하여 내부에서는 가변적이지만, 외부로 넘겨줄 때에는 불변 StateFlow로 변경하여 반환한다.
    //LiveData와는 달리 StateFlow는 초기값을 필요로 한다.
    private var _isSignedUser = MutableLiveData<Event<Boolean>>(Event(false))
    val isSignedUser: LiveData<Event<Boolean>> get() = _isSignedUser

    init {
        viewModelScope.launch {
            checkSignedUser()
        }
    }

    /** 자동 로그인 여부 체크*/
    private suspend fun checkSignedUser() {
        _isSignedUser.value =
            Event(ApplicationClass.getInstance().getUserManager().getUserInfo() != null)
    }
}