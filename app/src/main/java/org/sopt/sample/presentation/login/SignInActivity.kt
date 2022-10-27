package org.sopt.sample.presentation.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.sample.R
import org.sopt.sample.application.ApplicationClass
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySigninBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.util.const.*
import org.sopt.sample.util.extensions.makeSnackBar
import org.sopt.sample.util.extensions.showToast


class SignInActivity : BindingActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent> //회원가입 정보를 받아오기 위한 launcher
    private var idFromSignup: String? = null
    private var pwFromSignup: String? = null
    private var mbtiFromSignup: String? = null
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        editor = ApplicationClass.sSharedPreferences.edit()

        setResultSignUp() //회원가입 콜백
        signIn() //로그인
        signUp() //회원가입
        showPw() //비밀번호 노출
    }

    //Signup Callback
    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    //result.data에 대한 null체크
                    //toString()을 붙이면 null이 들어올 경우, 문자열 null로 바뀌어 들어올 수 있음.
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
            //SignUp에서 받아온 id/pw와 일치하는지 체크
            if (binding.signInIdEt.text.toString() == ApplicationClass.sSharedPreferences.getString(
                    USER_INFO_ID, null
                ) &&
                binding.signInPwEt.text.toString() == ApplicationClass.sSharedPreferences.getString(
                    USER_INFO_PW, null
                )
            ) {
                this.showToast(getString(R.string.signin_login_complete))

                //로그인 성공 시 id,pw,mbti를 sp에 저장
                //apply를 활용하여 가독성 up!
                editor.apply {
                    putString(
                        USER_INFO_ID, ApplicationClass.sSharedPreferences.getString(
                            USER_INFO_ID, null
                        )
                    )
                    putString(
                        USER_INFO_PW, ApplicationClass.sSharedPreferences.getString(
                            USER_INFO_PW, null
                        )
                    )
                    putString(
                        USER_INFO_MBTI, ApplicationClass.sSharedPreferences.getString(
                            USER_INFO_MBTI, null
                        )
                    )
                    commit()
                }
                editor.apply {
                    putString(
                        AUTO_LOGIN_ID, ApplicationClass.sSharedPreferences.getString(
                            USER_INFO_ID, null
                        )
                    )
                    putString(
                        AUTO_LOGIN_PW, ApplicationClass.sSharedPreferences.getString(
                            USER_INFO_PW, null
                        )
                    )
                    putString(
                        AUTO_LOGIN_MBTI, ApplicationClass.sSharedPreferences.getString(
                            USER_INFO_MBTI, null
                        )
                    )
                    commit()
                }

                val homeIntent = Intent(this, MainActivity::class.java)
                //apply와 also를 활용하여 가독성 up!
                homeIntent.apply {
                    putExtra(USER_INFO_ID, ApplicationClass.sSharedPreferences.getString(USER_INFO_ID, null))
                    putExtra(USER_INFO_PW, ApplicationClass.sSharedPreferences.getString(USER_INFO_PW, null))
                    putExtra(USER_INFO_MBTI, ApplicationClass.sSharedPreferences.getString(USER_INFO_MBTI,null))
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }.also { //apply는 자기자신(T)을 반환하기 때문에, homeIntent.also{} 처럼 가능
                    startActivity(it) //자기자신(T)를 반환하는 also를 it으로 활용하여 startActivity(it) 호출
                }
            }
            //로그인 실패
            else {
                binding.root.makeSnackBar(getString(R.string.signin_fail_id_or_pw))
                    .setAnchorView(binding.signInLoginBtn)
                    .show()
            }
        }
    }

    private fun signUp() {
        //회원가입 버튼
        val signupIntent = Intent(this, SignupActivity::class.java)
        binding.signInSignUpBtn.setOnClickListener {
            resultLauncher.launch(signupIntent) //데이터를 받아올 SignupActivity 실행

        }
    }

}