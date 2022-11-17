package org.sopt.sample.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.MainViewModel
import org.sopt.sample.presentation.home.data.HomeRepoContentData
import org.sopt.sample.presentation.home.data.HomeRepoTitleData
import org.sopt.sample.presentation.home.diffUtilAdapter.HomeRepoListAdapter
import org.sopt.sample.presentation.login.signin.SignInActivity
import org.sopt.sample.util.RecyclerDecorationHeight
import org.sopt.sample.util.extensions.showToast

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeRecycleList by lazy {
        listOf(
            HomeRepoTitleData(
                titleName = "지훈이의 레포지터리"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Park"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Lee"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Han"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Seok"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Kim"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Woo"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Hwang"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Jegal"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Sunwoo"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Kang"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Choi"
            ), HomeRepoContentData(
                profileImg = R.drawable.home_profile_img_bino,
                repoName = "IN_SOPT",
                authorName = "Jihun Son"
            )
        )
    }
    private val adapter by lazy {
        HomeRepoListAdapter(requireContext()).apply {
            submitList(
                homeRecycleList
            )
        }
    }

    //MainActivity 뷰모델 by 위임 생성
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        initAdapter()
        heightGapRv()
        logOutBtnListener()
    }

    /** 로그아웃 : sp를 초기화하고 SignInActivity Start */
    private fun logOutBtnListener() {
        binding.homeLogout.setOnClickListener {
            lifecycleScope.launch { //setUserInfo는 DataStore의 edit을 호출하기 때문에, 코루틴Scope 안에서 동작시켜야한다.
                ApplicationClass.getInstance().getUserManager().setUserInfo("", "", "") //유저 데이터 삭제
                showToast("로그아웃 되었습니다.")
                val intent = Intent(activity, SignInActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

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
private fun heightGapRv() {
    binding.homeRecyclerRepo.addItemDecoration(RecyclerDecorationHeight(80))
}

fun scrollToTop() {
    binding.homeRecyclerRepo.smoothScrollToPosition(0)
}
}