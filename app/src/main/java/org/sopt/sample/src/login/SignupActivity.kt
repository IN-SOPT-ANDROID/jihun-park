package org.sopt.sample.src.login

import android.content.Intent
import android.os.Bundle
import org.sopt.sample.R
import org.sopt.sample.config.BaseActivity
import org.sopt.sample.databinding.ActivitySignupBinding

class SignupActivity : BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate) {
    private val mbtiList: List<String> = listOf(
        "ENFJ", "ENTJ", "ENFP", "ENTP",
        "ESFP", "ESFJ", "ESTP", "ESTJ",
        "INFP", "INFJ", "INTP", "ISTP",
        "ISFP", "ISFJ", "ISTJ", "INTJ"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUp()

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
            CustomSnackBar(getString(R.string.signup_fail_id_length)).setAnchorView(binding.signUpIdEt)
                .show()
            return false
        }
    }

    private fun pwValidCheck(pw: String): Boolean {
        if (pw.length in 8..12) {
            return true
        } else {
            CustomSnackBar(getString(R.string.signup_fail_pw_length)).setAnchorView(binding.signUpPwEt)
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
            CustomSnackBar("올바르지 않은 MBTI입니다.").setAnchorView(binding.signUpMbtiEt).show()
            return false
        }
    }

    private fun signupSuccess() {
        //SignUp Success->HomeActivity
        val signInIntent = Intent(this, SignInActivity::class.java)
        signInIntent.putExtra("id", binding.signUpIdEt.text.toString())
        signInIntent.putExtra("pw", binding.signUpPwEt.text.toString())
        signInIntent.putExtra("mbti", binding.signUpMbtiEt.text.toString())
        setResult(RESULT_OK, signInIntent) //result code 및 intent 설정
        finish() //액티비티 종료
    }

}