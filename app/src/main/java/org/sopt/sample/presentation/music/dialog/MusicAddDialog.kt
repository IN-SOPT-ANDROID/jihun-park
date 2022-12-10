package org.sopt.sample.presentation.music.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.base.BindingDialog
import org.sopt.sample.databinding.DialogMusicAddBinding
import org.sopt.sample.presentation.music.MusicViewModel
import org.sopt.sample.presentation.state.UiState
import org.sopt.sample.util.extensions.showToast

@AndroidEntryPoint
class MusicAddDialog() : BindingDialog<DialogMusicAddBinding>(R.layout.dialog_music_add) {
    private val viewModel: MusicViewModel by viewModels()
    private val pickVmLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            binding.dialogImgIv.load(it)
            viewModel.uri.value = it
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.dialog = this
        initDialog()
        addListener()
        addObserver()

    }

    private fun initDialog() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

    private fun addListener() {
        binding.dialogImgIv.setOnClickListener {
            pickVmLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun addObserver() {
        viewModel.isDialogInputValid.observe(viewLifecycleOwner) {
            binding.dialogConfirmBtn.isEnabled = !it
        }
        viewModel.musicState.observe(viewLifecycleOwner) {
            if (it is UiState.Success) {
                dismissDialog()
                showToast(getString(R.string.success_to_upload_music))
            } else {
                showToast(getString(R.string.fail_to_upload_music))
            }
        }
    }

    fun dismissDialog() {
        with(binding) {
            dialogImgIv.load(null)
            dialogInputEtTitle.text = null
            dialogInputEtSinger.text = null
        }
        dialog?.dismiss()
    }
}
