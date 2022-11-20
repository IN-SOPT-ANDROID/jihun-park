package org.sopt.sample.util.extensions

import org.sopt.sample.data.home.model.User
import org.sopt.sample.data.home.model.UserInfo

fun List<UserInfo>.toUserList():List<User> = this.map { it.toUser() }

fun UserInfo.toUser() = User(
    email = this.email,
    avatar = this.avatar,
    name = this.firstname+this.lastname
)