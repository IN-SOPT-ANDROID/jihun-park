package org.sopt.sample.data.home.source

import org.sopt.sample.data.home.model.UserListResponse

interface HomeDataSource {
    suspend fun loadUser(page:Int): UserListResponse
}