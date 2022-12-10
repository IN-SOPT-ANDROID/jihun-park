package org.sopt.sample.util.extensions

import org.sopt.sample.data.model.Music
import org.sopt.sample.data.model.User
import org.sopt.sample.data.model.UserInfo
import org.sopt.sample.presentation.music.data.MusicInfo

fun List<UserInfo>.toUserList():List<User> = this.map { it.toUser() }

fun UserInfo.toUser() = User(
    email = this.email,
    avatar = this.avatar,
    name = this.firstname+this.lastname
)

fun List<Music>.toMusicInfo():List<MusicInfo> = this.map { it.toMusicInfo() }

fun Music.toMusicInfo() = MusicInfo(
    id = this.id,
    img = this.image,
    title = this.title,
    singer = this.singer
)