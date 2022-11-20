package org.sopt.sample.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sample.data.home.model.User
import org.sopt.sample.data.home.repository.HomeRepository
import org.sopt.sample.util.extensions.toUserList

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    val userList = MutableLiveData<List<User>>()
    private val _loadUserSuccess = MutableLiveData<Boolean>()
    val loadUserSuccess: LiveData<Boolean>
        get() = _loadUserSuccess


    fun loadUserList(page: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                homeRepository.loadUser(page)
            }.onSuccess {
                userList.value = it.data.toUserList() //서버에서 가져온 userList -> userList LiveData
                _loadUserSuccess.value = true
            }.onFailure {
                _loadUserSuccess.value = false
            }
        }
    }

}