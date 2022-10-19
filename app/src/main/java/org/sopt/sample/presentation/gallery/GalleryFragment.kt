package org.sopt.sample.presentation.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentGalleryBinding
import org.sopt.sample.presentation.gallery.data.GalleryRecycleData
import org.sopt.sample.presentation.gallery.diffUtilAdapter.GalleryListAdapter
import org.sopt.sample.presentation.home.HomeFragment


class GalleryFragment() : BindingFragment<FragmentGalleryBinding>(FragmentGalleryBinding::bind,R.layout.fragment_gallery) {

    private val dataList = listOf(
        GalleryRecycleData(R.drawable.gallery_recycle_ironman1,"아이언맨1"),
        GalleryRecycleData(R.drawable.gallery_recycle_ironman3,"아이언3"),
        GalleryRecycleData(R.drawable.gallery_recycle_spiderman_amazing,"어메이징 스파이더맨"),
        GalleryRecycleData(R.drawable.gallery_recycle_spiderman_farfromhome,"스파이더맨 파프롬홈"),
        GalleryRecycleData(R.drawable.gallery_recycle_spiderman_nowayhome,"스파이더맨 노웨이홈"),
        GalleryRecycleData(R.drawable.gallery_recycle_avengers_endgame,"어벤저스 엔드게임"),
        )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter(){
        val adapter = GalleryListAdapter(requireContext())
        binding.galleryRv.adapter = adapter
        binding.galleryRecyclerAddBtn.setOnClickListener {
            adapter.setData(dataList)
        }
    }
    companion object {
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }

    }
}