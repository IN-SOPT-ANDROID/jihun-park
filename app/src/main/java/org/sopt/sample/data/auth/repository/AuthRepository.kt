package org.sopt.sample.data.auth.repository

import org.sopt.sample.data.auth.model.SignInRequest
import org.sopt.sample.data.auth.model.SignInResponse
import org.sopt.sample.data.auth.model.SignUpRequest
import org.sopt.sample.data.auth.model.SignUpResponse
import org.sopt.sample.data.auth.source.AuthDataSource

class AuthRepository(private val authDataSource: AuthDataSource) {
    suspend fun signIn(signInRequest: SignInRequest):SignInResponse{
        return authDataSource.signIn(signInRequest)
    }
    suspend fun signUp(signUpRequest: SignUpRequest):SignUpResponse{
        return authDataSource.signUp(signUpRequest)
    }
}