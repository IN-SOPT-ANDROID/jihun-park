package org.sopt.sample.presentation.login.signin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySigninBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.presentation.common.ViewModelFactory
import org.sopt.sample.presentation.login.signup.SignupActivity
import org.sopt.sample.util.extensions.showToast


class SignInActivity : BindingActivity<ActivitySigninBinding>(R.layout.activity_signin) {
    private val viewModel: SignInViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        revealPw() //비밀번호 노출
        addListener()
        addObserver()


    }

    private fun addObserver() {
        //로그인 성공 여부 observe
        viewModel.signInSuccess.observe(this) {
            if (it) {
                showToast(getString(R.string.signin_login_success))
                moveToHome()
            } else {
                showToast(getString(R.string.signin_login_fail))
            }
        }
        //이상하게 observe를 하지 않으면 isInputValid가 값을 갱신하지 않는다.....
        //=> MediatorLiveData의 addSource에선 사전에 active observer가 attached된 상태인지를 체크하기 때문에
        //observe를 해줘야 addSource가 동작한다!
        viewModel.isInputValid.observe(this) {
        }
    }

    private fun addListener() {
        //로그인 버튼 클릭 시, 입력값 검사 후 로그인 서버통신
        binding.signInLoginBtn.setOnClickListener {

            if (viewModel.isInputValid.value == false) {
                showToast(getString(R.string.signin_empty_email_or_pw))
            } else {
                viewModel.signIn()
            }
        }
        //회원가입 버튼 클릭 시
        binding.signInSignUpBtn.setOnClickListener {
            moveToSignUp()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun revealPw() {
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
    } //비밀번호 드러내기

    private fun moveToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun moveToSignUp() {
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
    }
}