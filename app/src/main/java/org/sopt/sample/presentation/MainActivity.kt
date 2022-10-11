package org.sopt.sample.presentation

import android.os.Bundle
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.gallery.GalleryFragment
import org.sopt.sample.presentation.home.HomeFragment
import org.sopt.sample.presentation.search.SearchFragment

class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        logOutBtnListener()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_frame)
        //현재 설정되어있는 Fragment가 없으면, HomeFragment를 초기화면으로 설정
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_frame, HomeFragment.newInstance())
                .commit()
        }
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.mainBtmNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btm_home_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFragmentFrame.id, HomeFragment.newInstance())
                        .commit()
                    true
                }
                R.id.btm_gallery_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFragmentFrame.id, GalleryFragment.newInstance())
                        .commit()
                    true
                }
                R.id.btm_search_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFragmentFrame.id, SearchFragment.newInstance())
                        .commit()
                    true
                }
                else -> false
            }
        }
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