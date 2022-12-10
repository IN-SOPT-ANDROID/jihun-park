package org.sopt.sample.domain

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.model.MusicResponse
import org.sopt.sample.data.model.ResponseUploadMusic

interface MusicRepository {
    suspend fun fetchMusicList(): MusicResponse
    suspend fun uploadMusic(
        musicRequest: RequestBody,
        image: MultipartBody.Part
    ): ResponseUploadMusic
}