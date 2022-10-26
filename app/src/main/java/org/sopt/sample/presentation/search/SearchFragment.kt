package org.sopt.sample.presentation.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentSearchBinding
import org.sopt.sample.presentation.login.SignInActivity
import org.sopt.sample.util.extensions.showToast


class SearchFragment() :
    BindingFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}