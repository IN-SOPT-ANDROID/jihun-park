package org.sopt.sample.presentation

import android.os.Bundle
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.home.HomeFragment

class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        logOutBtnListener()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container)
        //현재 설정되어있는 Fragment가 없으면, HomeFragment를 초기화면으로 설정
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, HomeFragment.newInstance())
                .commit()
        }
    }

    private fun initBottomNavigation() {
    }


//    /** 로그아웃 : sp를 초기화하고 SignInActivity Start */
//    private fun logOutBtnListener(){
//        binding.homeLogoutBtn.setOnClickListener {
//            ApplicationClass.sSharedPreferences.edit().clear().commit() //모든 저장데이터 삭제
//            this.showToast("로그아웃 되었습니다.")
//            finish()
//            val intent = Intent(this,SignInActivity::class.java)
//            startActivity(intent)
//        }
//    }
}