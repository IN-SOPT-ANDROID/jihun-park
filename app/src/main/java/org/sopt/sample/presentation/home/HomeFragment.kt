package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.View
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.home.adapter.HomeRecyclerAdapter
import org.sopt.sample.presentation.home.data.HomeRecycleData
import org.sopt.sample.presentation.home.data.RepoContentDataHome
import org.sopt.sample.presentation.home.data.RepoTitleDataHome

class HomeFragment :
    BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private val homeRecycleList = listOf<HomeRecycleData>(
        RepoTitleDataHome(
            titleName = "지훈이의 레포지터리"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Park"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Lee"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Han"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Seok"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kim"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Woo"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Hwang"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Jegal"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Sunwoo"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kang"
        ),
        RepoContentDataHome(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Choi"
        ),
        RepoContentDataHome(
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