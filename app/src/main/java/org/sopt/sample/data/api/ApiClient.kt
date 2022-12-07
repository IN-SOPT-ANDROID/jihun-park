package org.sopt.sample.data.api

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.sample.BuildConfig
import org.sopt.sample.application.ApplicationClass
import retrofit2.Retrofit

object ApiClient {
    private const val AUTH_BASE_URL = BuildConfig.AUTH_BASE_URL
    private const val REQRES_BASE_URL = BuildConfig.REQRES_BASE_URL
    private var authRetrofit: Retrofit? = null
    private var reqresRetrofit: Retrofit? = null
    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .addNetworkInterceptor(FlipperOkhttpInterceptor(ApplicationClass.networkFlipperPlugin))
            .build()
    }
    //AUTH API
    @OptIn(ExperimentalSerializationApi::class, InternalCoroutinesApi::class)
    //동기화 처리를 통해 멀티쓰레드 환경에서 인스턴스가 2개 생성되는 것을 방지
    fun getRetrofitForAuth(): Retrofit? {
        synchronized(this) {
            if (authRetrofit == null) {
                authRetrofit = Retrofit.Builder()
                    .baseUrl(AUTH_BASE_URL)
                    .client(client)
                    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                    .build()
            }
            return authRetrofit
        }
    }
    //UserList API
    @OptIn(ExperimentalSerializationApi::class, InternalCoroutinesApi::class)
    @Synchronized
    fun getRetrofitForUserList(): Retrofit? {
        synchronized(this) {
            if (reqresRetrofit == null) {
                reqresRetrofit = Retrofit.Builder()
                    .baseUrl(REQRES_BASE_URL)
                    .client(client)
                    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                    .build()
            }
            return reqresRetrofit
        }
    }
}