package org.sopt.sample.src

import android.os.Bundle
import org.sopt.sample.config.BaseActivity
import org.sopt.sample.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    private lateinit var mbti:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbti = intent.getStringExtra("mbti").toString()
        binding.homeNameTv.text = "이름: 박지훈"
        "MBTI: $mbti".also { binding.homeMbtiTv.text = it }

    }
}