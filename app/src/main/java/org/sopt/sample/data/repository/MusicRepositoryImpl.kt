package org.sopt.sample.data.repository

import android.util.Log
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.model.MusicResponse
import org.sopt.sample.data.model.ResponseUploadMusic
import org.sopt.sample.data.source.remote.MusicDataSource
import org.sopt.sample.domain.MusicRepository

class MusicRepositoryImpl(private val musicDataSource: MusicDataSource) : MusicRepository {
    override suspend fun fetchMusicList(): MusicResponse = musicDataSource.fetchMusicList()
    override suspend fun uploadMusic(
        musicRequest: RequestBody,
        image: MultipartBody.Part
    ): ResponseUploadMusic {
        return musicDataSource.uploadMusic(musicRequest, image)}
}