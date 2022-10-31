package org.sopt.sample.presentation.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentSearchBinding
import org.sopt.sample.presentation.MainViewModel


class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    //MainActivity 뷰모델 by 위임 생성
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel


    }

}