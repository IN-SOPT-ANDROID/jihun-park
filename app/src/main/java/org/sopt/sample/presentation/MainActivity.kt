package org.sopt.sample.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.gallery.GalleryFragment
import org.sopt.sample.presentation.home.HomeFragment
import org.sopt.sample.presentation.search.SearchFragment

class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        //현재 설정되어있는 Fragment가 없으면, HomeFragment를 초기화면으로 설정
        addListener()
        changeFragment(R.id.btm_home_menu)
    }

    private fun addListener() {
        binding.mainBtmNavigation.setOnItemSelectedListener {
            changeFragment(it.itemId)
            true
        }
        binding.mainBtmNavigation.setOnItemReselectedListener {
            if (it.itemId != R.id.btm_home_menu) return@setOnItemReselectedListener
            (supportFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName) as? HomeFragment)?.scrollToTop()
        }
    }

    private fun changeFragment(menuItemId: Int) {
        when (menuItemId) {
            R.id.btm_home_menu -> supportFragmentManager.commit {
                replace<HomeFragment>(
                    R.id.main_fragment_frame,
                    HomeFragment::class.java.simpleName
                )
            }
            R.id.btm_gallery_menu -> supportFragmentManager.commit {
                replace<GalleryFragment>(
                    R.id.main_fragment_frame,
                    GalleryFragment::class.java.simpleName
                )
            }
            R.id.btm_search_menu -> supportFragmentManager.commit {
                replace<SearchFragment>(
                    R.id.main_fragment_frame,
                    SearchFragment::class.java.simpleName
                )
            }
            else -> IllegalArgumentException("${this::class.java.simpleName} Not found menu item id")
        }
    }
//        logOutBtnListener()
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