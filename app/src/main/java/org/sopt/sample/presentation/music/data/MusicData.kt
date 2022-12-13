package org.sopt.sample.presentation.music.data

sealed class MusicData

const val MUSIC_INFO_TYPE = 0
const val MUSIC_ADD_TYPE = 1

data class MusicInfo(val id: Int, val img: String, val title: String, val singer: String) :
    MusicData()

data class MusicAdd(val addRes:Int) :
    MusicData()
