package org.sopt.sample.data.home.Service

import org.sopt.sample.data.home.model.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    //유저 목록 가져오기
    @GET("/api/users?page=")
    suspend fun loadUserList(
        @Query("page") page: Int = 2
    ): UserListResponse
}