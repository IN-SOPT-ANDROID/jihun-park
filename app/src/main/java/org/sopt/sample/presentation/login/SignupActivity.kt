package org.sopt.sample.presentation.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySignupBinding
import org.sopt.sample.presentation.model.UserInfo
import org.sopt.sample.util.EventObserver
import org.sopt.sample.util.extensions.showToast

class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel: SignViewModel by viewModels()

    companion object {
        //inputType Password 값
        const val INPUT_TYPE_PASSWORD = 129
        const val ARG_USER_INFO = "userInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
        addObservers()
        revealPw()
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun revealPw() {
        Log.d("Signup", "showPw touch 상태 ${binding.signUpPwEt.inputType}")
        binding.signupShowPw.setOnTouchListener { v, event ->
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
    private fun addListeners(){
        binding.signUpCompleteBtn.setOnClickListener {
            viewModel.inputValidCheck()
        }
    }
    private fun addObservers(){
        //asLiveData - 뷰가 STOPPED되면 자동 소비 관리
        //addListeners()를 통해 viewModel.inputValidCheck()가 호출되면, isSignUpInputValid의 값이 변경
        //isSignUpInputValid를 observe 하고있다가 signUpSuccess에 값을 넘겨준다.
        viewModel.isSignUpInputValid.asLiveData().observe(this, EventObserver { isValid ->
            lifecycleScope.launch {
                signUpSuccess(isValid)
            }
        })
    }
    private fun signUpSuccess(isValid: Boolean) {
        if(isValid){
            showToast(getString(R.string.signup_login_success))
            //DataStore에 로그인 정보 저장(자동 로그인을 위함) - 백그라운드에서 수행
            CoroutineScope(Dispatchers.IO).launch {
                with(binding) {
                    ApplicationClass.getInstance().getUserManager().setUserInfo(
                        signUpIdEt.text.toString(),
                        signUpPwEt.text.toString(),
                        signUpMbtiEt.text.toString()
                    )
                }
            }
            //SignUp Success->SignInActivity로 전달
            val intent = Intent(this, SignInActivity::class.java)
            with(binding) {
                intent.putExtra(
                    ARG_USER_INFO,
                    UserInfo(
                        signUpIdEt.text.toString(),
                        signUpPwEt.text.toString(),
                        signUpMbtiEt.text.toString()
                    )
                )
            }
            setResult(RESULT_OK, intent) //result code 및 intent 설정
            finish() //액티비티 종료
        }
        else {
            when(viewModel.failReason){
                SignUpFail.ID_FAIL-> showToast(getString(R.string.signup_fail_id_length))
                SignUpFail.PW_FAIL-> showToast(getString(R.string.signup_fail_pw_length))
                SignUpFail.MBTI_FAIL-> showToast(getString(R.string.signup_fail_mbti))
            }
        }
    }
}