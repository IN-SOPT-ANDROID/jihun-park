package org.sopt.sample.data.auth.source

import org.sopt.sample.data.auth.model.SignInRequest
import org.sopt.sample.data.auth.model.SignInResponse
import org.sopt.sample.data.auth.model.SignUpRequest
import org.sopt.sample.data.auth.model.SignUpResponse

interface AuthDataSource {
    suspend fun signIn(signInRequest: SignInRequest):SignInResponse
    suspend fun signUp(signUpRequest: SignUpRequest):SignUpResponse
}