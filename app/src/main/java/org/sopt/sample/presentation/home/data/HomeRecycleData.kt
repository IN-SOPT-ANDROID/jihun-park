package org.sopt.sample.presentation.home.data


abstract class HomeRecycleData {
    companion object HomeConst{
        const val REPO_TITLE_TYPE = 0
        const val REPO_CONTENT_TYPE = 1
    }
    abstract val viewType:Int
}
data class RepoTitleDataHome(
    val titleName: String,
    override val viewType: Int = REPO_TITLE_TYPE
): HomeRecycleData()

data class RepoContentDataHome(
    val profileImg: Int,
    val repoName: String,
    val authorName: String,
    override val viewType: Int = REPO_CONTENT_TYPE
): HomeRecycleData()
