package org.sopt.sample.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.sample.BuildConfig
import retrofit2.Retrofit

object ApiClient {
    private const val AUTH_BASE_URL = BuildConfig.AUTH_BASE_URL
    private const val REQRES_BASE_URL = BuildConfig.REQRES_BASE_URL
    private var retrofit: Retrofit? = null

    //AUTH API
    @OptIn(ExperimentalSerializationApi::class, InternalCoroutinesApi::class)
    //특정 스레드가 retrofit 변수를 사용 중일 때, 다른 스레드의 접근을 막아 데이터 안전성 보장 -> Thread Safe
    fun getRetrofitForAuth(): Retrofit? {
        retrofit?.let {
            synchronized(it) {
                if (retrofit == null) {
                    val logger = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    val client = OkHttpClient.Builder()
                        .addInterceptor(logger)
                        .build()
                    retrofit = Retrofit.Builder()
                        .baseUrl(AUTH_BASE_URL)
                        .client(client)
                        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                        .build()
                }
            }
        }
        return retrofit
    }

    //UserList API
    @OptIn(ExperimentalSerializationApi::class, InternalCoroutinesApi::class)
    fun getRetrofitForUserList(): Retrofit? {
        retrofit?.let {
            synchronized(it) {
                if (retrofit == null) {
                    val logger = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    val client = OkHttpClient.Builder()
                        .addInterceptor(logger)
                        .build()
                    retrofit = Retrofit.Builder()
                        .baseUrl(REQRES_BASE_URL)
                        .client(client)
                        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                        .build()
                }
            }
        }
        return retrofit
    }
}