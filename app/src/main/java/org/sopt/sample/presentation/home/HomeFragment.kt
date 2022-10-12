package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.View
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.home.adapter.HomeRecyclerAdapter
import org.sopt.sample.presentation.home.data.RecycleData


class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::bind,R.layout.fragment_home) {

    private val homeRecycleList = listOf<RecycleData>(
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Park"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Lee"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Han"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Seok"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kim"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Woo"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Hwang"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Jegal"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Sunwoo"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kang"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Choi"
        ),
        RecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Son"
        )
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeRecyclerAdapter(requireContext())
        binding.homeRecyclerRepo.adapter = adapter
        adapter.setDataList(homeRecycleList)
    }

    companion object {
        fun newInstance():HomeFragment{
            return HomeFragment()
        }
    }
}