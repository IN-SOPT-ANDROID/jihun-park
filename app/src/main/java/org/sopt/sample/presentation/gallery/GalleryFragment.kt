package org.sopt.sample.presentation.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentGalleryBinding
import org.sopt.sample.presentation.gallery.data.GalleryRecycleData
import org.sopt.sample.presentation.gallery.diffUtilAdapter.GalleryListAdapter
import org.sopt.sample.presentation.home.HomeFragment
import org.sopt.sample.util.RecyclerDecorationHeight


class GalleryFragment() : BindingFragment<FragmentGalleryBinding>(FragmentGalleryBinding::bind,R.layout.fragment_gallery) {

    private val dataList = listOf(
        GalleryRecycleData(R.drawable.gallery_recycle_ironman1,"아이언맨1"),
        GalleryRecycleData(R.drawable.gallery_recycle_ironman3,"아이언맨3"),
        GalleryRecycleData(R.drawable.gallery_recycle_spiderman_amazing,"어메이징\n스파이더맨"),
        GalleryRecycleData(R.drawable.gallery_recycle_spiderman_farfromhome,"스파이더맨\n파프롬홈"),
        GalleryRecycleData(R.drawable.gallery_recycle_spiderman_nowayhome,"스파이더맨\n노웨이홈"),
        GalleryRecycleData(R.drawable.gallery_recycle_avengers_endgame,"어벤저스\n엔드게임"),
        GalleryRecycleData(R.drawable.gallery_recycle_docter_multiverse,"닥터스트레인지\n대혼돈의 멀티버스"),
        GalleryRecycleData(R.drawable.gallery_recycle_civilwar,"캡틴아메리카\n시빌워"),
        GalleryRecycleData(R.drawable.gallery_recycle_thor_darkworld,"토르\n다크월크"),
        )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        heightGapRv()
    }

    private fun initAdapter(){
        val adapter = GalleryListAdapter(requireContext())
        binding.galleryRv.layoutManager = GridLayoutManager(requireContext(),3)
        binding.galleryRv.adapter = adapter
            adapter.submitList(dataList)
        }
    //리사이클러 뷰 간격(높이)
    private fun heightGapRv(){
        binding.galleryRv.addItemDecoration(RecyclerDecorationHeight(80))
    }

}