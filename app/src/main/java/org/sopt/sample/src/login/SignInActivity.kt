package org.sopt.sample.src.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import org.sopt.sample.config.BaseActivity
import org.sopt.sample.databinding.ActivitySigninBinding
import org.sopt.sample.src.HomeActivity


class SignInActivity : BaseActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signupIntent = Intent(this, SignupActivity::class.java)
        val homeIntent = Intent(this, HomeActivity::class.java)

        signIn(homeIntent)
        signUp(signupIntent)


    }
    private fun signIn(homeIntent:Intent){
        //로그인 버튼 클릭
        binding.signInLoginBtn.setOnClickListener {
            //SignUp에서 받아온 id/pw와 일치하는지 체크
            if(binding.signInIdEt.text.toString()==(intent.getStringExtra("id")) &&
                binding.signInPwEt.text.toString()==(intent.getStringExtra("pw"))){
                startActivity(homeIntent)
            }
            //로그인 실패
            else{
                CustomSnackBar("ID 또는 PW를 확인해주세요!").setAnchorView(binding.signInLoginBtn).show()
            }
        }
    }
    private fun signUp(signupIntent:Intent){
        //회원가입 버튼 클릭
        binding.signInSignUpBtn.setOnClickListener {
            startActivity(signupIntent)
        }
    }
}