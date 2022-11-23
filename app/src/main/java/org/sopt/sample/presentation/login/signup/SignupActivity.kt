package org.sopt.sample.presentation.login.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySignupBinding
import org.sopt.sample.presentation.common.ViewModelFactory
import org.sopt.sample.presentation.login.signin.SignInActivity
import org.sopt.sample.util.extensions.showToast

class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel: SignUpViewModel by viewModels { ViewModelFactory() }

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


    }

    private fun addObserver() {
        //입력값 상태에 따른 회원가입 버튼 활성화<->비활성화
        viewModel.isInputValid.observe(this) {
            binding.signUpCompleteBtn.apply {
                if (it) {
                    background = AppCompatResources.getDrawable(
                        this@SignupActivity,
                        R.drawable.signup_btn_border
                    )
                } else {
                    background = AppCompatResources.getDrawable(
                        this@SignupActivity,
                        R.drawable.signup_btn_border_disable
                    )
                }
            }
        }
        //회원가입 성공 여부 observe
        viewModel.signUpSuccess.observe(this) {
            if (it) {
                showToast(getString(R.string.signup_login_success))
                moveToSignIn()
            } else {
                showToast(getString(R.string.signup_login_fail))
            }
        }
        //name,email, pw 유효성 검사
        viewModel.name.observe(this){
            binding.signUpNameInput.apply {
                if (!viewModel.isNameValid) {
                    isErrorEnabled = true
                    error = " "
                } else {
                    isErrorEnabled = false
                }
            }
        }
        viewModel.email.observe(this) {
            binding.signUpEmailInput.apply {
                if (!viewModel.isEmailValid) {
                    isErrorEnabled = true
                    error = " "
                } else {
                    isErrorEnabled = false
                }
            }
        }
        viewModel.pw.observe(this) {
            binding.signUpPwInput.apply {
                if (!viewModel.isPwValid) {
                    isErrorEnabled = true
                    error = " "
                } else {
                    isErrorEnabled = false
                }
            }
        }
    }

    //입력값 valid check ->  signUp():서버통신 -> signUpSuccess값 변경 -> 회원가입 success or fail
    private fun addListener() {
        //회원가입 버튼 클릭 시, 입력값 검사 후 회원가입 서버통신
        binding.signUpCompleteBtn.setOnClickListener {
            if (!viewModel.isNameValid) {
                showToast(getString(R.string.signup_fail_name))
            } else if (!viewModel.isEmailValid) {
                showToast(getString(R.string.signup_fail_email))
            } else if (!viewModel.isPwValid) {
                showToast(getString(R.string.signup_fail_pw_length))
            } else {
                viewModel.signUp()
            }
        }
        //뒤로가기 버튼
        binding.signUpBackBtn.setOnClickListener {
            moveToSignIn()
        }
    }

    private fun moveToSignIn() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}