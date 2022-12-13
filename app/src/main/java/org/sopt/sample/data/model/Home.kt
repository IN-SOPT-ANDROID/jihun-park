package org.sopt.sample.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    @SerialName("avatar") val avatar: String,
    @SerialName("email") val email: String,
    @SerialName("first_name") val firstname: String,
    @SerialName("id") val id: Int,
    @SerialName("last_name") val lastname: String
)

@Serializable
data class Support(
    @SerialName("text") val text: String,
    @SerialName("url") val url: String
)

@Serializable
data class UserListResponse(
    @SerialName("data") val data: List<UserInfo>,
    @SerialName("page") val page: Int,
    @SerialName("per_page") val perpage: Int,
    @SerialName("support") val support: Support,
    @SerialName("total") val total: Int,
    @SerialName("total_pages") val totalpages: Int
)

data class User(
    val avatar: String,
    val email: String,
    val name: String
)
