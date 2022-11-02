package org.sopt.sample.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySplashBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.presentation.login.SignInActivity
import org.sopt.sample.util.EventObserver
import org.sopt.sample.util.extensions.showToast

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimation() //화면 전환 애니메이션
        //뷰모델 초기화
        binding.viewModel = viewModel
        //Splash Activity의 별도 코드실행 없이 생명주기를 다룰 수 있도록 함.
        binding.lifecycleOwner = this

        addObserver()
    }

    private fun setAnimation() {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    //뷰모델의 StateFlow isSignedUser 관찰
    //뷰모델에서 checkSignedUser함수의 호출에 따라 isSignedUser의 값이 변경되면, 이후 코드 실행
    private fun addObserver() {
        //LiveData.observe - 뷰가 STOPPED 상태가 되면소비자를 자동으로 등록 취소한다.
        //StateFlow - 자동으로 중지하지 않기 때문에, lifecycleScope로 생명주기를 인지시켜주거나, lifecycle-livedata-ktx의 확장함수 asLiveData로 해결 가능
        viewModel.isSignedUser.asLiveData().observe(this, EventObserver { isSigned ->
            lifecycleScope.launch {
                delay(2000)
                moveToNext(isSigned)
                finish()
            }
        })
    }
    private fun moveToNext(isSigned: Boolean) {
        //isSigned(자동 로그인 여부)에 따라 MainActivity 또는 SignInActivity로 StartActivity
        when (isSigned) {
            true -> {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                this@SplashActivity.showToast(getString(R.string.signin_auto_login_complete))
            }
            false -> startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
        }
    }
}

