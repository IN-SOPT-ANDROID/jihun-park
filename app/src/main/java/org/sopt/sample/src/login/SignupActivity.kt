package org.sopt.sample.src.login

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.config.BaseActivity
import org.sopt.sample.databinding.ActivityHomeBinding.inflate
import org.sopt.sample.databinding.ActivitySignupBinding

class SignupActivity : BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val signInIntent = Intent(this, SignInActivity::class.java)

        binding.signUpCompleteBtn.setOnClickListener {
            //ID CHECK - Length Success
            if (binding.signUpIdEt.text.length in 6..10) {
                //PW CHECK - Length Success
                if (binding.signUpPwEt.text.length in 8..12) {
                    //SignUp Success->HomeActivity
                    signInIntent.putExtra("id",binding.signUpIdEt.text.toString())
                    signInIntent.putExtra("pw",binding.signUpPwEt.text.toString())
                    signInIntent.putExtra("mbti",binding.signUpMbtiEt.text.toString())
                    setResult(RESULT_OK,signInIntent) //result code 및 intent 설정
                    finish() //액티비티 종료
                }
                //PW CHECK - Length Fail
                else {
                    CustomSnackBar(getString(R.string.signup_fail_pw_length)).setAnchorView(binding.signUpPwEt).show()
                }
            }
            //ID CHECK - Length Fail
            else {
                CustomSnackBar(getString(R.string.signup_fail_id_length)).setAnchorView(binding.signUpIdEt).show()
            }
        }
    }
}