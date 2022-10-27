package org.sopt.sample.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    //LiveData
    var userId = MutableLiveData<String>()
    var userMbti = MutableLiveData<String>()
    fun receiveUserData(id:String,mbti:String){
        userId.value = "ID: $id"
        userMbti.value = "MBTI: $mbti"
    }
}