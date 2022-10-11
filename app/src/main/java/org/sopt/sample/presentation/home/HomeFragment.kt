package org.sopt.sample.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding


class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::bind,R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance():HomeFragment{
            return HomeFragment()
        }
    }
}