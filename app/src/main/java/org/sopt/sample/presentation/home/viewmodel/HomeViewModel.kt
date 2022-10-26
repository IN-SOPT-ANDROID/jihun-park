package org.sopt.sample.presentation.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.util.const.USER_INFO_ID
import org.sopt.sample.util.const.USER_INFO_MBTI

class HomeViewModel:ViewModel() {

    //LiveData
    var userId = MutableLiveData<String>()
    var userMbti = MutableLiveData<String>()
    init {
        userId.value = "ID: ".plus(ApplicationClass.sSharedPreferences.getString(USER_INFO_ID,null).toString())
        userMbti.value = "MBTI: ".plus(ApplicationClass.sSharedPreferences.getString(USER_INFO_MBTI,null).toString())
    }


}