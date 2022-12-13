package org.sopt.sample.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.sample.data.model.User
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

    fun loadUserList(page: Int) {
        viewModelScope.launch {
            runCatching {
                _homeState.value = UiState.Loading
                delay(1000)
                homeRepository.loadUser(page)
            }.onSuccess {
                userList.value = it.data.toUserList() //서버에서 가져온 userList -> userList LiveData
                _homeState.value = UiState.Success
            }.onFailure {
                _homeState.value = UiState.Failure
            }
        }

    }
}