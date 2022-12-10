package org.sopt.sample.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.home.adapter.HomeUserListAdapter
import org.sopt.sample.presentation.state.UiState
import org.sopt.sample.util.RecyclerDecorationHeight
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        val adapter = HomeUserListAdapter(requireContext())
        binding.homeRv.adapter = adapter
        viewModel.loadUserList(2) //page 수 Query
        addObserver(adapter)
        addDecorator()
    }

    //load 성공 여부 observe 후 adapter로 userList 전달
    private fun addObserver(adapter: HomeUserListAdapter) {
        viewModel.homeState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    showSampleData()
                }
                is UiState.Success -> {
                    viewModel.userList.value?.let { userList -> adapter.submitUserList(userList) }
                    hideSampleData()
                }
                else -> {
                    hideSampleData()
                }
            }

        }
    }

    //리사이클러 뷰 간격(높이)
    private fun addDecorator() {
        binding.homeRv.addItemDecoration(RecyclerDecorationHeight(80))
    }

    private fun showSampleData() {
        binding.homeSfl.apply {
            startShimmer()
            visibility = View.VISIBLE
            binding.homeRv.visibility = View.GONE
        }
    }

    private fun hideSampleData() {
        binding.homeSfl.apply {
            stopShimmer()
            visibility = View.GONE
            binding.homeRv.visibility = View.VISIBLE
        }
    }
}