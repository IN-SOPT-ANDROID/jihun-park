package org.sopt.sample.presentation.login.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySignupBinding
import org.sopt.sample.presentation.login.signin.SignInActivity
import org.sopt.sample.util.extensions.showToast

class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel: SignUpViewModel by viewModels()

    companion object {
        //inputType Password 값
        const val INPUT_TYPE_PASSWORD = 129
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        addObserver()
        addListener()
        revealPw()
    }

        //뒤로가기 버튼
        binding.signUpBackBtn.setOnClickListener {
            moveToSignIn()
        }
    @SuppressLint("ClickableViewAccessibility")
    private fun revealPw() {
        binding.signupShowPw.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.signUpPwEt.inputType = InputType.TYPE_CLASS_TEXT
                }
                MotionEvent.ACTION_MOVE -> {
                    binding.signUpPwEt.inputType = InputType.TYPE_CLASS_TEXT
                }
                MotionEvent.ACTION_UP -> {
                    binding.signUpPwEt.inputType = INPUT_TYPE_PASSWORD
                }
            }
            true
        }
    }
    private fun addObserver() {
        viewModel.isInputValid.observe(this) {
            binding.signUpCompleteBtn.apply {
                if (it) {
                    setBackgroundColor(getColor(R.color.sign_btn))
                } else {
                    setBackgroundColor(getColor(R.color.sign_btn_disable))
                }
            }
        }
    }

    private fun addListener() {
        binding.signUpCompleteBtn.setOnClickListener {
            if (viewModel.isInputValid.value == false) {
                showToast(getString(R.string.signup_fail_id_or_pw_length))
            }
        }
    }

    private fun signUpSuccess(isValid: Boolean) {
        if (isValid) {
            showToast(getString(R.string.signup_login_success))
            startActivity(Intent(this, SignInActivity::class.java))
            finish() //액티비티 종료
        } else {
        }
    }
}