package org.sopt.sample.presentation.music

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentMusicBinding
import org.sopt.sample.presentation.music.adapter.MusicListAdapter
import org.sopt.sample.presentation.music.data.MusicInfo

class MusicFragment : BindingFragment<FragmentMusicBinding>(R.layout.fragment_music) {

    private val adapter by lazy {
        MusicListAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        binding.musicRv.layoutManager = LinearLayoutManager(requireContext())
        adapter.setData(
            listOf(
                MusicInfo(
                    "https://sopt-31th-bucket.s3.ap-northeast-2.amazonaws.com/e3cbc3cd-f8c2-4cd5-bf45-49a10eb4ad36.jpeg",
                    "d",
                    "asd"
                ),
                MusicInfo(
                    "https://sopt-31th-bucket.s3.ap-northeast-2.amazonaws.com/e3cbc3cd-f8c2-4cd5-bf45-49a10eb4ad36.jpeg",
                    "d",
                    "sa"
                )
            )
        )
        binding.musicRv.adapter = adapter

    }
}