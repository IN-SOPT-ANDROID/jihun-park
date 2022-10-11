package org.sopt.sample.presentation.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySignupBinding
import org.sopt.sample.util.const.*
import org.sopt.sample.util.extensions.makeSnackBar

class SignupActivity : BindingActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate) {
    private val mbtiList: List<String> = listOf(
        "ENFJ", "ENTJ", "ENFP", "ENTP",
        "ESFP", "ESFJ", "ESTP", "ESTJ",
        "INFP", "INFJ", "INTP", "ISTP",
        "ISFP", "ISFJ", "ISTJ", "INTJ"
    )
    companion object{
        //inputType Password 값
        const val INPUT_TYPE_PASSWORD = 129
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUp()
        showPw()
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun showPw() {
        Log.d("Signup","showPw touch 상태 ${binding.signUpPwEt.inputType}")
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


    private fun signUp() {
        binding.signUpCompleteBtn.setOnClickListener {
            if (idValidCheck(binding.signUpIdEt.text.toString()) && pwValidCheck(binding.signUpPwEt.text.toString()) && mbtiValidCheck(
                    binding.signUpMbtiEt.text.toString()
                )
            ) {
                signupSuccess()
            }
        }
    } //root func(idValidCheck,pwValidCheck,signupSuccess)

    private fun idValidCheck(id: String): Boolean {
        if (id.length in 6..10) {
            return true
        } else {
            binding.root.makeSnackBar(getString(R.string.signup_fail_id_length)).setAnchorView(binding.signUpIdEt)
                .show()
            return false
        }
    }

    private fun pwValidCheck(pw: String): Boolean {
        if (pw.length in 8..12) {
            return true
        } else {
            binding.root.makeSnackBar(getString(R.string.signup_fail_pw_length)).setAnchorView(binding.signUpPwEt)
                .show()
            return false
        }
    }

    private fun mbtiValidCheck(mbti: String?): Boolean {
        if (mbti?.isEmpty() == true || binding.signUpMbtiEt.text.toString()
                .uppercase() in mbtiList
        ) {
            return true
        } else {
            binding.root.makeSnackBar("올바르지 않은 MBTI입니다.").setAnchorView(binding.signUpMbtiEt).show()
            return false
        }
    }

    private fun signupSuccess() {
        //SignUp Success->HomeActivity
        Intent(this, SignInActivity::class.java).apply {
            putExtra(USER_INFO_ID, binding.signUpIdEt.text.toString())
            putExtra(USER_INFO_PW, binding.signUpPwEt.text.toString())
            putExtra(USER_INFO_MBTI, binding.signUpMbtiEt.text.toString())
        }.also {
            setResult(RESULT_OK, it) //result code 및 intent 설정
            finish() //액티비티 종료
        }
    }

}