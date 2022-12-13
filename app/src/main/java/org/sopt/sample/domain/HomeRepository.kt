package org.sopt.sample.domain

import org.sopt.sample.data.model.UserListResponse

interface HomeRepository {
    suspend fun loadUser(page: Int): UserListResponse
}