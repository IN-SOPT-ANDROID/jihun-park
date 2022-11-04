package org.sopt.sample.presentation.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySigninBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.util.EventObserver
import org.sopt.sample.util.const.USER_INFO_ID
import org.sopt.sample.util.const.USER_INFO_MBTI
import org.sopt.sample.util.const.USER_INFO_PW
import org.sopt.sample.util.extensions.makeSnackBar
import org.sopt.sample.util.extensions.showToast


class SignInActivity : BindingActivity<ActivitySigninBinding>(R.layout.activity_signin) {
    private val viewModel: SignViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent> //회원가입 정보를 받아오기 위한 launcher
    private var idFromSignup: String? = null
    private var pwFromSignup: String? = null
    private var mbtiFromSignup: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResultSignUp() //회원가입 콜백
        signIn() //로그인
        moveTosignUp() //회원가입
        showPw() //비밀번호 노출

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    //Signup Callback
    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    //result.data null체크
                    result.data?.let {
                        idFromSignup = it.getStringExtra(USER_INFO_ID)
                        pwFromSignup = result.data?.getStringExtra(USER_INFO_PW)
                        mbtiFromSignup = result.data?.getStringExtra(USER_INFO_MBTI)
                    }

                    binding.root.makeSnackBar(getString(R.string.signin_success_signup))
                        .setAnchorView(binding.signInLoginBtn)
                        .show()
                }
                //회원가입 취소
                else {
                    binding.root.makeSnackBar(getString(R.string.signin_fail_signup))
                        .setAnchorView(binding.signInLoginBtn)
                        .show()
                }
            }
    }

    private fun addListeners() {
        binding.signInLoginBtn.setOnClickListener {
            viewModel.signIn()
        }

        binding.signInSignUpBtn.setOnClickListener {
            resultLauncher.launch(Intent(this, SignupActivity::class.java))
        }
    }

    private fun addObservers() {
        viewModel.isSignInSuccess.asLiveData().observe(this, EventObserver { isCompleted ->
            showToast(getString(if (isCompleted) {
                moveToHome()
                R.string.sign_in_success_toast_message
            } else {
                R.string.sign_in_fail_toast_message
            }))
        })
    }



    @SuppressLint("ClickableViewAccessibility")
    private fun showPw() {
        Log.d("Signup", "showPw touch 상태 ${binding.signInPwEt.inputType}")
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
        binding.signInLoginBtn.setOnClickListener {
            CoroutineScope(Main).launch {
                //DataStore의 id/pw와 일치하는지 체크
                if (binding.signInIdEt.text.toString() == ApplicationClass.getInstance()
                        .getUserManager().userIdFlow.first() &&
                    binding.signInPwEt.text.toString() == ApplicationClass.getInstance()
                        .getUserManager().userIdFlow.first()
                ) {
                    showToast(getString(R.string.signin_login_complete))
                }
                //로그인 실패
                else {
                    binding.root.makeSnackBar(getString(R.string.signin_fail_id_or_pw))
                        .setAnchorView(binding.signInLoginBtn)
                        .show()
                }
            }
        }
    }

    private fun moveToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun moveTosignUp() {
        //회원가입 버튼
        val signupIntent = Intent(this, SignupActivity::class.java)
        binding.signInSignUpBtn.setOnClickListener {
            resultLauncher.launch(signupIntent) //데이터를 받아올 SignupActivity 실행
        }
    }
}