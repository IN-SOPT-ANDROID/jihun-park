package org.sopt.sample.presentation.search

import android.os.Bundle
import android.view.View
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentSearchBinding


class SearchFragment() :
    BindingFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}