package org.sopt.sample.presentation.music.data

sealed class MusicData

data class MusicInfo(val img: String, val title:String, val singer:String):MusicData()

