package org.sopt.sample.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize //UserInfo를 Parcelable로 취급해서 Intent에서 다룰 수 있도록 함.
data class UserInfo(
    val id: String,
    val pw: String,
    val mbti: String
):Parcelable
