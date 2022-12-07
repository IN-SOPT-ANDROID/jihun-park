package org.sopt.sample.presentation.sign.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputLayout
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
                inputErrorCheck(this, viewModel.isNameValid)
            }
        }
        viewModel.id.observe(this) {
            binding.signUpIdInput.apply {
                inputErrorCheck(this, viewModel.isIdValid)
            }
        }
        viewModel.pw.observe(this) {
            binding.signUpPwInput.apply {
                inputErrorCheck(this, viewModel.isPwValid)
            }
        }
    }
    //입력값 valid check ->  signUp():서버통신 -> signUpSuccess값 변경 -> 회원가입 success or fail
    fun moveToSignIn() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    private fun inputErrorCheck(textInputLayout: TextInputLayout, isValid: Boolean) {
        textInputLayout.apply {
            if (!isValid) {
                isErrorEnabled = true
                error = " "
            } else {
                isErrorEnabled = false
            }
        }
    }
}