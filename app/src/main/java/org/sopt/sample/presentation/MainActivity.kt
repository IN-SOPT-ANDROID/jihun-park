package org.sopt.sample.presentation

import android.content.Intent
import android.os.Bundle
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.login.SignInActivity
import org.sopt.sample.util.const.USER_INFO_MBTI
import org.sopt.sample.util.extensions.showToast

class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var mbti:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        logOutBtnListener()
    }
    private fun init(){
        mbti = intent.getStringExtra(USER_INFO_MBTI).toString()
        binding.homeNameTv.text = getString(R.string.home_user_name)
        binding.homeMbtiTv.text = "${getString(R.string.home_mbti_tv)} $mbti"

    }

    /** 로그아웃 : sp를 초기화하고 SignInActivity Start */
    private fun logOutBtnListener(){
        binding.homeLogoutBtn.setOnClickListener {
            ApplicationClass.sSharedPreferences.edit().clear().commit() //모든 저장데이터 삭제
            this.showToast("로그아웃 되었습니다.")
            finish()
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
    }
}