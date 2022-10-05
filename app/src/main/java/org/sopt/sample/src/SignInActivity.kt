package org.sopt.sample.src

import android.content.Intent
import android.os.Bundle
import org.sopt.sample.config.BaseActivity
import org.sopt.sample.databinding.ActivitySigninBinding


class SignInActivity : BaseActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signupIntent = Intent(this,SignupActivity::class.java)
        val homeIntent = Intent(this,SignupActivity::class.java)

        //로그인 버튼 클릭
        binding.signInLoginBtn.setOnClickListener {
            startActivity(homeIntent)
        }
        //회원가입 버튼 클릭
        binding.signInSignUpBtn.setOnClickListener {
            startActivity(signupIntent)
        }

    }
}