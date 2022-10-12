package org.sopt.sample.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.home.adapter.HomeRecyclerAdapter
import org.sopt.sample.presentation.home.data.HomeRecycleData


class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::bind,R.layout.fragment_home) {

    private val homeRecycleList = listOf<HomeRecycleData>(
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Park"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Lee"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Han"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Seok"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kim"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Woo"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Hwang"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Jegal"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Sunwoo"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Kang"
        ),
        HomeRecycleData(
            profileImg = R.drawable.home_profile_img_bino,
            repoName = "IN_SOPT",
            authorName = "Jihun Choi"
        ),
        HomeRecycleData(
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