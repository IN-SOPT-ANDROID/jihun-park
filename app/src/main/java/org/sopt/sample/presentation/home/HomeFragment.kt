package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.common.ViewModelFactory
import org.sopt.sample.presentation.home.adapter.HomeUserListAdapter
import org.sopt.sample.util.RecyclerDecorationHeight

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        val adapter = HomeUserListAdapter(requireContext())
        binding.homeRv.adapter = adapter
        viewModel.loadUserList(2) //page 수 Query
        addObserver(adapter)
        heightGapRv()
    }

    //load 성공 여부 observe 후 adapter로 userList 전달
    private fun addObserver(adapter: HomeUserListAdapter) {
        viewModel.loadUserSuccess.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.userList.value?.let { it1 -> adapter.submitUserList(it1) }
            }
        }
    }

    //리사이클러 뷰 간격(높이)
    private fun heightGapRv() {
        binding.homeRv.addItemDecoration(RecyclerDecorationHeight(80))
    }

//    /** 로그아웃 : sp를 초기화하고 SignInActivity Start */
//    private fun logOutBtnListener() {
//        binding.homeLogout.setOnClickListener {
//            lifecycleScope.launch { //setUserInfo는 DataStore의 edit을 호출하기 때문에, 코루틴Scope 안에서 동작시켜야한다.
//                ApplicationClass.getInstance().getUserManager().setUserInfo("", "", "") //유저 데이터 삭제
//                showToast("로그아웃 되었습니다.")
//                val intent = Intent(activity, SignInActivity::class.java)
//                startActivity(intent)
//                activity?.finish()
//            }
//        }
//    }
}