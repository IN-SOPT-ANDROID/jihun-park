package org.sopt.sample.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.home.data.HomeRepoContentData
import org.sopt.sample.presentation.home.data.HomeRepoTitleData
import org.sopt.sample.presentation.home.diffUtilAdapter.HomeRepoListAdapter
import org.sopt.sample.presentation.login.SignInActivity
import org.sopt.sample.presentation.viewmodel.MainViewModel
import org.sopt.sample.util.RecyclerDecorationHeight
import org.sopt.sample.util.const.AUTO_LOGIN_ID
import org.sopt.sample.util.const.AUTO_LOGIN_MBTI
import org.sopt.sample.util.const.AUTO_LOGIN_PW
import org.sopt.sample.util.extensions.showToast

class HomeFragment : Fragment() {
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
    private lateinit var hBinding: FragmentHomeBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //데이터 바인딩 연결 및 ViewModel 선언
        hBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return hBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        heightGapRv()
        logOutBtnListener()
        hBinding.viewModel = viewModel
    }

    /** 로그아웃 : sp를 초기화하고 SignInActivity Start */
    private fun logOutBtnListener() {
        hBinding.homeLogout.setOnClickListener {
            ApplicationClass.sSharedPreferences.edit().apply {
                putString(AUTO_LOGIN_ID, null)
                putString(AUTO_LOGIN_PW, null)
                putString(AUTO_LOGIN_MBTI, null)
            }.commit() //자동 로그인 데이터 삭제
            this.showToast("로그아웃 되었습니다.")
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }


    private fun initAdapter() {
        hBinding.homeRecyclerRepo.adapter = adapter
        //add 버튼 -> Repo 추가
        hBinding.homeRecyclerRepoAddBtn.setOnClickListener {
            adapter.add()
        }
        //remove 버튼 -> Repo 삭제
        hBinding.homeRecyclerRepoRemoveBtn.setOnClickListener {
            adapter.remove()
        }

    }

    //리사이클러 뷰 간격(높이)
    private fun heightGapRv() {
        hBinding.homeRecyclerRepo.addItemDecoration(RecyclerDecorationHeight(80))
    }

    fun scrollToTop() {
        hBinding.homeRecyclerRepo.smoothScrollToPosition(0)
    }
}