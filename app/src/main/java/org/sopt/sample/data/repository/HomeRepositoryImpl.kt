package org.sopt.sample.data.repository

import org.sopt.sample.data.model.UserListResponse
import org.sopt.sample.data.source.remote.HomeDataSource
import org.sopt.sample.domain.HomeRepository

class HomeRepositoryImpl(private val homeDataSource: HomeDataSource) : HomeRepository {
    override suspend fun loadUser(page: Int): UserListResponse = homeDataSource.loadUser(page)
}