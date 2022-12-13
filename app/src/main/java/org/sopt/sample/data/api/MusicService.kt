package org.sopt.sample.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.model.MusicResponse
import org.sopt.sample.data.model.ResponseUploadMusic
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MusicService {
    // 음악 리스트 가져오기
    @GET("/music/list")
    suspend fun fetchMusicList(): MusicResponse

    //음악 생성하기
    @Multipart
    @POST("/music")
    suspend fun uploadMusic(
        @Part("request") request: RequestBody,
        @Part image: MultipartBody.Part
    ):ResponseUploadMusic
}