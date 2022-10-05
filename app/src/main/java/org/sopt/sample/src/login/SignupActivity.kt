package org.sopt.sample.src.login

import android.content.Intent
import android.os.Bundle
import org.sopt.sample.R
import org.sopt.sample.config.BaseActivity
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
                    showCustomToast(getString(R.string.signup_login_success))
                    //SignUp Success->HomeActivity
                    signInIntent.putExtra("id",binding.signUpIdEt.text.toString())
                    signInIntent.putExtra("pw",binding.signUpPwEt.text.toString())
                    signInIntent.putExtra("mbti",binding.signUpMbtiEt.text.toString())
                    startActivity(signInIntent)
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