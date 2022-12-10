package org.sopt.sample.data.source.remote

import org.sopt.sample.data.api.HomeService
import org.sopt.sample.data.model.UserListResponse

class HomeDataSource(private val homeService: HomeService) {
    suspend fun loadUser(page: Int): UserListResponse =
        homeService.loadUserList(page)
}