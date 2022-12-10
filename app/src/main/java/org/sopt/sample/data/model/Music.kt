package org.sopt.sample.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MusicResponse(
    val data: List<Music>,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)

@Serializable
data class ResponseUploadMusic(
    val data: Music,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)

@Serializable
data class Music(
    val id: Int,
    val image: String,
    val singer: String,
    val title: String
)

