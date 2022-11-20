package org.sopt.sample.data.auth.source

import org.sopt.sample.data.auth.Service.AuthService
import org.sopt.sample.data.auth.model.SignInRequest
import org.sopt.sample.data.auth.model.SignInResponse
import org.sopt.sample.data.auth.model.SignUpRequest
import org.sopt.sample.data.auth.model.SignUpResponse

class AuthDataSourceImpl(private val authService: AuthService):AuthDataSource {
    override suspend fun signIn(signInRequest: SignInRequest): SignInResponse {
        return authService.signIn(signInRequest)
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): SignUpResponse {
        return authService.signUp(signUpRequest)
    }
}