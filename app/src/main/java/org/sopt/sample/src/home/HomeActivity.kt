package org.sopt.sample.src.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.application.ApplicationClass.Companion.USER_INFO_MBTI
import org.sopt.sample.application.BindingActivity
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.src.login.SignInActivity
import org.sopt.sample.util.extensions.showToast

class HomeActivity : BindingActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    private lateinit var mbti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationClass.sSharedPreferences

        mbti = intent.getStringExtra(USER_INFO_MBTI).toString()
        binding.homeNameTv.text = getString(R.string.home_user_name)
        "MBTI: $mbti".also { binding.homeMbtiTv.text = it }
        logOut()
    }

    /** 로그아웃 : sp를 초기화하고 SignInActivity Start */
    private fun logOut() {
        binding.homeLogoutBtn.setOnClickListener {
            ApplicationClass.sSharedPreferences.edit().clear().commit() //모든 저장데이터 삭제
            this.showToast("로그아웃 되었습니다.")
            finish()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}