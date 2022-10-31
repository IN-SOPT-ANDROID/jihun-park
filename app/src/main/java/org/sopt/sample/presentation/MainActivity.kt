package org.sopt.sample.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.gallery.GalleryFragment
import org.sopt.sample.presentation.home.HomeFragment
import org.sopt.sample.presentation.search.SearchFragment


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main){

    private val viewModel: MainViewModel by viewModels()

    companion object {
        lateinit var mContext: Context
        const val USER_INFO_ID = "id"
        const val USER_INFO_PW = "pw"
        const val USER_INFO_MBTI = "mbti"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        //DataBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.setUserData(intent.getStringExtra(USER_INFO_ID).toString(),intent.getStringExtra(USER_INFO_MBTI).toString())


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

}