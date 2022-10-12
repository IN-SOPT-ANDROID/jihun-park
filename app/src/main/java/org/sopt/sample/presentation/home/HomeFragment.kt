package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.home.adapter.HomeRecyclerAdapter
import org.sopt.sample.presentation.home.data.RecycleData
import org.sopt.sample.presentation.home.data.RepoContentData
import org.sopt.sample.presentation.home.data.RepoTitleData

class HomeFragment :
    BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private val homeRecycleList = listOf<RecycleData>(
        RepoTitleData(
            titleName = "지훈이의 레포지터리"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Park"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Lee"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Han"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Seok"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kim"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Woo"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Hwang"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Jegal"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Sunwoo"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kang"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Choi"
        ),
        RepoContentData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Son"
        )
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = HomeRecyclerAdapter(requireContext())
        binding.homeRecyclerRepo.adapter = adapter
        adapter.setDataList(homeRecycleList)
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}