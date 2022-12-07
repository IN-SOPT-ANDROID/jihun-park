package org.sopt.sample.domain

import org.sopt.sample.data.home.model.UserListResponse
import org.sopt.sample.data.source.remote.HomeDataSource

interface HomeRepository {
    suspend fun loadUser(page: Int): UserListResponse
}