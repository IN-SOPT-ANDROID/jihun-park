package org.sopt.sample.data.home.source

import org.sopt.sample.data.home.service.HomeService
import org.sopt.sample.data.home.model.UserListResponse

class HomeDataSourceImpl(private val homeService: HomeService) : HomeDataSource {
    override suspend fun loadUser(page: Int): UserListResponse = homeService.loadUserList(page)

}