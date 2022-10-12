package org.sopt.sample.presentation.home.data

data class RepoContentData(
    val profileImg: Int,
    val repoName: String,
    val authorName: String,
    override val viewType: Int = REPO_CONTENT_TYPE
): RecycleData()
