package org.sopt.sample.presentation.login.signin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySigninBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.presentation.login.signup.SignupActivity


class SignInActivity : BindingActivity<ActivitySigninBinding>(R.layout.activity_signin) {
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        signIn() //로그인
        moveTosignUp() //회원가입
        showPw() //비밀번호 노출

    }


    private fun addListeners() {

    }

    private fun addObservers() {

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showPw() {
        binding.signInShowPw.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.signInPwEt.inputType = InputType.TYPE_CLASS_TEXT
                }
                MotionEvent.ACTION_MOVE -> {
                    binding.signInPwEt.inputType = InputType.TYPE_CLASS_TEXT
                }
                MotionEvent.ACTION_UP -> {
                    binding.signInPwEt.inputType = SignupActivity.INPUT_TYPE_PASSWORD
                }
            }
            true
        }
    }

    private fun signIn() {
        //로그인 버튼 클릭
    }

    private fun moveToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun moveTosignUp() {
        //회원가입 버튼
        val signupIntent = Intent(this, SignupActivity::class.java)
        binding.signInSignUpBtn.setOnClickListener {
            startActivity(signupIntent)
        }
    }
}