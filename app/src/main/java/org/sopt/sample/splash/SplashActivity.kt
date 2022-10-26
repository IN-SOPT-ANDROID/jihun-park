package org.sopt.sample.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AlphaAnimation
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySplashBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.presentation.login.SignInActivity
import org.sopt.sample.presentation.search.SearchFragment
import org.sopt.sample.util.const.*
import org.sopt.sample.util.extensions.showToast

class SplashActivity : BindingActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //sp에 로그인 정보가 있으면, MainActivity로
        //없으면 SignInActivity로
        //3초 로딩 후 SigninActivity
        Thread {
            handler.postDelayed({
                autoLogin()
            },3000)
        }.start()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    /** 자동 로그인 여부 */
    private fun isAutoLogin(): Boolean {
        //로그인 성공 했을 때 저장해놓은 정보를 HomeActivity에 보내면서 자동 로그인
        val id = ApplicationClass.sSharedPreferences.getString(AUTO_LOGIN_ID, null)
        val pw = ApplicationClass.sSharedPreferences.getString(AUTO_LOGIN_PW, null)
        return (id != null && pw != null)
    }
    private fun autoLogin() {
        if (isAutoLogin()) {
            Log.d(SearchFragment::class.java.simpleName,"자동 로그인 중")
            val intent = Intent(this, MainActivity::class.java)
            intent.apply {
                putExtra(
                    USER_INFO_ID,
                    ApplicationClass.sSharedPreferences.getString(USER_INFO_ID, null)
                )
                putExtra(
                    USER_INFO_PW,
                    ApplicationClass.sSharedPreferences.getString(USER_INFO_PW, null)
                )
                putExtra(
                    USER_INFO_MBTI,
                    ApplicationClass.sSharedPreferences.getString(USER_INFO_MBTI, null)
                )
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                this@SplashActivity.showToast(getString(R.string.signin_auto_login_complete))
                startActivity(this)
            }
        } else {
            Log.d(SearchFragment::class.java.simpleName,"로그인 화면 이동중")
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}