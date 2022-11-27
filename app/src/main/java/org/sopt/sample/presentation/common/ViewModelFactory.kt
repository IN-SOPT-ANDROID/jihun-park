package org.sopt.sample.presentation.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.sample.data.api.ApiClient
import org.sopt.sample.data.auth.service.AuthService
import org.sopt.sample.data.auth.repository.AuthRepository
import org.sopt.sample.data.auth.source.AuthDataSourceImpl
import org.sopt.sample.data.home.service.HomeService
import org.sopt.sample.data.home.repository.HomeRepository
import org.sopt.sample.data.home.source.HomeDataSourceImpl
import org.sopt.sample.presentation.home.HomeViewModel
import org.sopt.sample.presentation.login.signin.SignInViewModel
import org.sopt.sample.presentation.login.signup.SignUpViewModel

//ViewModelFactory는 주로 ViewModel에 매개변수를 전달하고자 할 때 사용
//할당되는 ViewModel에 따라 다른 ViewModel에 retrofit객체를 생성해서 초기화 후 return
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(
                    AuthRepository(
                        AuthDataSourceImpl(
                            ApiClient.getRetrofitForAuth()!!.create(AuthService::class.java)
                        )
                    )
                ) as T
            }
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(
                    AuthRepository(
                        AuthDataSourceImpl(
                            ApiClient.getRetrofitForAuth()!!.create(AuthService::class.java)
                        )
                    )
                ) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(
                    HomeRepository(
                        HomeDataSourceImpl(
                            ApiClient.getRetrofitForUserList()!!.create(HomeService::class.java)
                        )
                    )
                ) as T
            }


            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}