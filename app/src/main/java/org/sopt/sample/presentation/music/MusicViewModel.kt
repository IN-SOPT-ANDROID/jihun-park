package org.sopt.sample.presentation.music

import android.net.Uri
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.domain.MusicRepository
import org.sopt.sample.presentation.music.data.MusicData
import org.sopt.sample.presentation.state.UiState
import org.sopt.sample.util.ContentUriRequestBody
import org.sopt.sample.util.addSourceList
import org.sopt.sample.util.extensions.toJsonRequestBody
import org.sopt.sample.util.extensions.toMusicInfo
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicRepository: MusicRepository,
    private val application: ApplicationClass
) :
    ViewModel() {

    private val _musicState = MutableLiveData<UiState>(UiState.Loading)
    val musicState: LiveData<UiState>
        get() = _musicState

    val musicList = MutableLiveData<List<MusicData>>()

    val title = MutableLiveData<String>()
    val singer = MutableLiveData<String>()
    val uri = MutableLiveData<Uri>()
    val isDialogInputValid = MediatorLiveData<Boolean>()

    init {
        checkInputValid()
    }

    fun fetchMusicList() {
        viewModelScope.launch {
            runCatching {
                _musicState.value = UiState.Loading
                musicRepository.fetchMusicList()
            }.onSuccess {
                musicList.value = it.data.toMusicInfo()
                _musicState.value = UiState.Success
            }.onFailure {
                _musicState.value = UiState.Failure
                Timber.e(it.message)
            }
        }
    }

    fun uploadMusic() {
        viewModelScope.launch {
            val imageMultipartBody = ContentUriRequestBody(
                application.baseContext,
                uri.value!!
            ).toFormData()
            val jsonRequestBody =
                JSONObject("{\"singer\":\"${singer.value}\",\"title\":\"${title.value}\"}").toString()
                    .toJsonRequestBody()
            runCatching {
                _musicState.value = UiState.Loading
                musicRepository.uploadMusic(jsonRequestBody, imageMultipartBody)
            }.onSuccess {

                fetchMusicList()//음악 추가 후 리스트 불러오기
            }.onFailure {
                _musicState.value = UiState.Failure
                Timber.e(it.message)
            }
        }
    }

    private fun checkInputValid() {
        isDialogInputValid.addSourceList(title, singer, uri) {
            uri.value == null || title.value.isNullOrEmpty() || singer.value.isNullOrEmpty()
        }
    }
}