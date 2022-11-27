package org.sopt.sample.data.auth.source

import org.sopt.sample.data.auth.service.AuthService
import org.sopt.sample.data.auth.model.SignInRequest
import org.sopt.sample.data.auth.model.SignInResponse
import org.sopt.sample.data.auth.model.SignUpRequest
import org.sopt.sample.data.auth.model.SignUpResponse

interface AuthDataSource {
    suspend fun signIn(signInRequest: SignInRequest):SignInResponse
    suspend fun signUp(signUpRequest: SignUpRequest):SignUpResponse
}
class AuthDataSourceImpl(private val authService: AuthService) : AuthDataSource {
    override suspend fun signIn(signInRequest: SignInRequest): SignInResponse =
        authService.signIn(signInRequest)

    override suspend fun signUp(signUpRequest: SignUpRequest): SignUpResponse =
        authService.signUp(signUpRequest)
}