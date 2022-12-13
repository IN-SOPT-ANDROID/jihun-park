package org.sopt.sample.data.source.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.api.MusicService
import org.sopt.sample.data.model.MusicResponse
import org.sopt.sample.data.model.ResponseUploadMusic

class MusicDataSource(private val musicService: MusicService) {
    suspend fun fetchMusicList(): MusicResponse = musicService.fetchMusicList()

    suspend fun uploadMusic(
        musicRequest: RequestBody,
        image: MultipartBody.Part
    ): ResponseUploadMusic {
        return musicService.uploadMusic(musicRequest, image)
    }
}