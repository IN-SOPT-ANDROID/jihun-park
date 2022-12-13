package org.sopt.sample.presentation.music

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentMusicBinding
import org.sopt.sample.presentation.music.adapter.MusicListAdapter
import org.sopt.sample.presentation.music.data.MusicAdd
import org.sopt.sample.presentation.state.UiState

@AndroidEntryPoint
class MusicFragment : BindingFragment<FragmentMusicBinding>(R.layout.fragment_music) {
    private val viewModel: MusicViewModel by viewModels()

    private val adapter by lazy {
        MusicListAdapter(
            requireContext(),
            childFragmentManager
        ) //childFragmentManager 음악 추가 버튼 클릭 시, 다이얼로그 show() 호출을 위해 전달하는 인자
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.fetchMusicList()
        addObserver()
        initAdapter()
    }

    private fun addObserver() {
        viewModel.musicState.observe(viewLifecycleOwner) {
            if (it is UiState.Success) {
                viewModel.musicList.value?.let { data ->
                    adapter.setData(data)
                    adapter.addData(MusicAdd(R.drawable.ic_baseline_add_circle_outline_24))
                }
            }
        }
    }

    private fun initAdapter() {
        binding.musicRv.layoutManager = LinearLayoutManager(requireContext())
        binding.musicRv.adapter = adapter
    }

}