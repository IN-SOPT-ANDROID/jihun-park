package org.sopt.sample.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.sample.data.home.model.User
import org.sopt.sample.domain.HomeRepository
import org.sopt.sample.presentation.state.UiState
import org.sopt.sample.util.extensions.toUserList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    val userList = MutableLiveData<List<User>>()

    private val _homeState = MutableLiveData<UiState>(UiState.Loading)
    val homeState: LiveData<UiState>
        get() = _homeState

    private val _loadUserSuccess = MutableLiveData<Boolean>()
    val loadUserSuccess: LiveData<Boolean>
        get() = _loadUserSuccess

    //로딩 시작 시, isLoading = true
    //로딩이 끝나면, isLoading = false
    //Fragment에서 isLoading을 observe
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadUserList(page: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                _homeState.value = UiState.Loading
                delay(3000)
                homeRepository.loadUser(page)
            }.onSuccess {
                _homeState.value = UiState.Success
                userList.value = it.data.toUserList() //서버에서 가져온 userList -> userList LiveData
                _loadUserSuccess.value = true
            }.onFailure {
                _homeState.value = UiState.Failure
                _loadUserSuccess.value = false
            }
        }

    }


}