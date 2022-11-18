package org.sopt.sample.data.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    @SerialName("message") val message: String,
    @SerialName("result")val result: AuthResult,
    @SerialName("status")val status: Int
)
@Serializable
data class SignInRequest(
    @SerialName("email")val email: String,
    @SerialName("password")val password: String
)
@Serializable
data class SignUpRequest(
    @SerialName("email")val email: String,
    @SerialName("name")val name: String,
    @SerialName("password")val password: String
)
@Serializable
data class SignUpResponse(
    @SerialName("message")val message: String,
    @SerialName("newUser")val newUser: AuthResult,
    @SerialName("status")val status: Int
)
@Serializable
data class AuthResult(
    @SerialName("bio")val bio: String?,
    @SerialName("email")val email: String,
    @SerialName("id")val id: Int,
    @SerialName("name")val name: String,
    @SerialName("password")val password: String,
    @SerialName("profileImage")val profileImage: String?
)