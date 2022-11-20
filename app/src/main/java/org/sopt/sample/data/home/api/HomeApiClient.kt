package org.sopt.sample.data.home.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object HomeApiClient {
    private const val BASE_URL = "https://reqres.in"
    private var retrofit: Retrofit? = null

    @OptIn(ExperimentalSerializationApi::class)
    @Synchronized //특정 스레드가 사용 중일 때, 다른 스레드의 접근을 막아 데이터 안전성 보장 -> Thread Safe
    fun getInstance(): Retrofit? {
        if (retrofit == null) {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()
        }
        return retrofit
    }
}