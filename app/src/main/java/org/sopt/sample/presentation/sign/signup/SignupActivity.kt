package org.sopt.sample.presentation.sign.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySignupBinding
import org.sopt.sample.presentation.sign.signin.SignInActivity
import org.sopt.sample.util.extensions.showToast

@AndroidEntryPoint
class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
//    private val viewModel: SignUpViewModel by viewModels { ViewModelFactory() }
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


    }

    private fun addObserver() {

        //회원가입 성공 여부 observe
        viewModel.signUpSuccess.observe(this) {
            if (it) {
                showToast(getString(R.string.signup_login_success))
                moveToSignIn()
            } else {
                showToast(getString(R.string.signup_login_fail))
            }
        }
        //name,id, pw 유효성 검사
        viewModel.name.observe(this) {
            binding.signUpNameInput.apply {
                if (!viewModel.isNameValid) {
                    isErrorEnabled = true
                    error = " "
                } else {
                    isErrorEnabled = false
                }
            }
        }
        viewModel.id.observe(this) {
            binding.signUpIdInput.apply {
                if (!viewModel.isIdValid) {
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