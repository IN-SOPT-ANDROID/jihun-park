package org.sopt.sample.data.home.source

import org.sopt.sample.data.home.Service.HomeService
import org.sopt.sample.data.home.model.UserListResponse

class HomeDataSourceImpl(private val homeService: HomeService):HomeDataSource {
    override suspend fun loadUser(page: Int): UserListResponse {
        return homeService.loadUserList(page)
    }
}