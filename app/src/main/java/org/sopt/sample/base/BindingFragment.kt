package org.sopt.sample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

// Fragment의 기본을 작성, 뷰 바인딩 활용
abstract class BindingFragment<B : ViewBinding>(
    private val bind: (View) -> B,
) : Fragment() {
    private var _binding: B? = null
    private val binding
        get() = requireNotNull(_binding!!)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}