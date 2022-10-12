package org.sopt.sample.presentation.home.data

data class RepoTitleData(
    val titleName: String,
    override val viewType: Int = REPO_TITLE_TYPE
): RecycleData()
