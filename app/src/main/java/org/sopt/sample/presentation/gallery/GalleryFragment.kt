package org.sopt.sample.presentation.gallery

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentGalleryBinding
import org.sopt.sample.presentation.gallery.data.GalleryRecycleData
import org.sopt.sample.presentation.gallery.diffUtilAdapter.GalleryListAdapter
import org.sopt.sample.presentation.MainViewModel
import org.sopt.sample.util.RecyclerDecorationHeight
import org.sopt.sample.util.selection.SelectionTracker


class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {

    private val dataList by lazy {
        listOf(
            GalleryRecycleData(1,R.drawable.gallery_recycle_ironman1, "아이언맨1"),
            GalleryRecycleData(2,R.drawable.gallery_recycle_ironman3, "아이언맨3"),
            GalleryRecycleData(3,R.drawable.gallery_recycle_spiderman_amazing, "어메이징\n스파이더맨"),
            GalleryRecycleData(4,R.drawable.gallery_recycle_spiderman_farfromhome, "스파이더맨\n파프롬홈"),
            GalleryRecycleData(5,R.drawable.gallery_recycle_spiderman_nowayhome, "스파이더맨\n노웨이홈"),
            GalleryRecycleData(6,R.drawable.gallery_recycle_avengers_endgame, "어벤저스\n엔드게임"),
            GalleryRecycleData(7,R.drawable.gallery_recycle_docter_multiverse, "닥터스트레인지\n대혼돈의 멀티버스"),
            GalleryRecycleData(8,R.drawable.gallery_recycle_civilwar, "캡틴아메리카\n시빌워"),
            GalleryRecycleData(9,R.drawable.gallery_recycle_thor_darkworld, "토르\n다크월크"),
        )
    }
    private val adapter by lazy { GalleryListAdapter(requireContext()).apply { submitList(dataList) } }

    //MainActivity 뷰모델 by 위임 생성
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        initAdapter()
        heightGapRv()
    }

    private fun initAdapter() {
        binding.galleryRv.layoutManager = GridLayoutManager(requireContext(),3)
        binding.galleryRv.adapter = adapter
        initTracker()
    }

    //리사이클러 뷰 간격(높이)
    private fun heightGapRv() {
        binding.galleryRv.addItemDecoration(RecyclerDecorationHeight(80))
    }

    private fun initTracker() {
        Log.d(
            GalleryFragment::class.java.simpleName,
            SelectionTracker().setupSelectionTracker(binding.galleryRv).toString()
        )
        adapter.setSelectionTracker(SelectionTracker().setupSelectionTracker(binding.galleryRv))
    }

}