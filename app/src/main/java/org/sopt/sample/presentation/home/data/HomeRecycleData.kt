package org.sopt.sample.presentation.home.data


sealed class HomeRecycleData
const val REPO_TITLE_TYPE = 0
const val REPO_CONTENT_TYPE = 1
data class HomeRepoTitleData(val titleName: String): HomeRecycleData()
data class HomeRepoContentData(val profileImg: Int, val repoName: String, val authorName: String) : HomeRecycleData()



