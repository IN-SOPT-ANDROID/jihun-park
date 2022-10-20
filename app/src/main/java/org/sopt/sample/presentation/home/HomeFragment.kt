package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.View
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.home.data.HomeRecycleData
import org.sopt.sample.presentation.home.data.HomeRepoContentData
import org.sopt.sample.presentation.home.data.HomeRepoTitleData
import org.sopt.sample.presentation.home.diffUtilAdapter.HomeRepoListAdapter
import org.sopt.sample.util.RecyclerDecorationHeight

class HomeFragment :
    BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    private val homeRecycleList by lazy {
        listOf(
        HomeRepoTitleData(
            titleName = "지훈이의 레포지터리"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Park"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Lee"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Han"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Seok"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kim"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Woo"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Hwang"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Jegal"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Sunwoo"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kang"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Choi"
        ),
        HomeRepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Son"
        )
    )}
    private val adapter by lazy { HomeRepoListAdapter(requireContext()).apply { submitList(homeRecycleList) } }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        heightGapRv()

    }

    private fun initAdapter() {
        binding.homeRecyclerRepo.adapter = adapter
        //add 버튼 -> Repo 추가
        binding.homeRecyclerRepoAddBtn.setOnClickListener {
            adapter.add()
        }
        //remove 버튼 -> Repo 삭제
        binding.homeRecyclerRepoRemoveBtn.setOnClickListener {
            adapter.remove()
        }

    }
    //리사이클러 뷰 간격(높이)
    private fun heightGapRv(){
        binding.homeRecyclerRepo.addItemDecoration(RecyclerDecorationHeight(80))
    }

    fun scrollToTop() {
        binding.homeRecyclerRepo.smoothScrollToPosition(0)
    }
}