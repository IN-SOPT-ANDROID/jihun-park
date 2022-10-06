package org.sopt.sample.src.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.sample.R
import org.sopt.sample.config.BaseActivity
import org.sopt.sample.databinding.ActivitySigninBinding
import org.sopt.sample.src.HomeActivity


class SignInActivity : BaseActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent> //회원가입 정보를 받아오기 위한 launcher
    private var idFromSignup: String? = null
    private var pwFromSignup: String? = null
    private var mbtiFromSignup: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setResultSignUp() //회원가입 콜백
        signIn() //로그인
        signUp() //회원가입

    }

    //Signup Callback
    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    idFromSignup = result.data?.getStringExtra("id").toString()
                    pwFromSignup = result.data?.getStringExtra("pw").toString()
                    mbtiFromSignup = result.data?.getStringExtra("mbti").toString()
                    CustomSnackBar(getString(R.string.signin_success_signup)).setAnchorView(binding.signInLoginBtn)
                        .show()
                }
                //회원가입 취소
                else {
                    CustomSnackBar(getString(R.string.signin_fail_signup)).setAnchorView(binding.signInLoginBtn)
                        .show()
                }
            }
    }

    private fun signIn() {
        //로그인 버튼 클릭
        binding.signInLoginBtn.setOnClickListener {
            //SignUp에서 받아온 id/pw와 일치하는지 체크
            if (binding.signInIdEt.text.toString() == idFromSignup &&
                binding.signInPwEt.text.toString() == (pwFromSignup)
            ) {
                showCustomToast("로그인에 성공했습니다")
                val homeIntent = Intent(this, HomeActivity::class.java)
                homeIntent.putExtra("id", idFromSignup)
                homeIntent.putExtra("pw", pwFromSignup)
                homeIntent.putExtra("mbti", mbtiFromSignup)
                startActivity(homeIntent)
            }
            //로그인 실패
            else {
                CustomSnackBar(getString(R.string.signin_fail_id_or_pw)).setAnchorView(binding.signInLoginBtn)
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