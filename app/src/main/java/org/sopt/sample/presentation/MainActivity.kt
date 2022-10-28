package org.sopt.sample.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.gallery.GalleryFragment
import org.sopt.sample.presentation.home.HomeFragment
import org.sopt.sample.presentation.search.SearchFragment
import org.sopt.sample.presentation.viewmodel.MainViewModel
import org.sopt.sample.util.const.USER_INFO_ID
import org.sopt.sample.util.const.USER_INFO_MBTI


class MainActivity : AppCompatActivity(){

    private lateinit var mBinding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        //DataBinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.lifecycleOwner = this
        model.setUserData(intent.getStringExtra(USER_INFO_ID).toString(),intent.getStringExtra(USER_INFO_MBTI).toString())
        mBinding.viewModel = model

        //현재 설정되어있는 Fragment가 없으면, HomeFragment를 초기화면으로 설정
        addListener()
        changeFragment(R.id.btm_home_menu)
    }

    private fun addListener() {
        mBinding.mainBtmNavigation.setOnItemSelectedListener {
            changeFragment(it.itemId)
            true
        }
        mBinding.mainBtmNavigation.setOnItemReselectedListener {
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