package org.sopt.sample.presentation.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentGalleryBinding
import org.sopt.sample.presentation.home.HomeFragment


class GalleryFragment() : BindingFragment<FragmentGalleryBinding>(FragmentGalleryBinding::bind,R.layout.fragment_gallery) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }

    }
}