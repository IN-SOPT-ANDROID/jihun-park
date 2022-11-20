package org.sopt.sample.data.home.repository

import org.sopt.sample.data.home.model.UserListResponse
import org.sopt.sample.data.home.source.HomeDataSource

class HomeRepository(private val homeDataSource: HomeDataSource) {
    suspend fun loadUser(page: Int): UserListResponse = homeDataSource.loadUser(page)

}