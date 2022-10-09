package org.sopt.sample.src.login

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
import org.sopt.sample.config.*
import org.sopt.sample.databinding.ActivitySigninBinding
import org.sopt.sample.src.home.HomeActivity
import org.sopt.sample.util.extensions.makeSnackBar
import org.sopt.sample.util.extensions.showToast


class SignInActivity : BindingActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent> //회원가입 정보를 받아오기 위한 launcher
    private var idFromSignup: String? = null
    private var pwFromSignup: String? = null
    private var mbtiFromSignup: String? = null
    private lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        editor = ApplicationClass.sSharedPreferences.edit()

        autoLogin()//자동 로그인
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
                    idFromSignup = result.data?.getStringExtra("id").toString()
                    pwFromSignup = result.data?.getStringExtra("pw").toString()
                    mbtiFromSignup = result.data?.getStringExtra("mbti").toString()

                    binding.root.makeSnackBar(getString(R.string.signin_success_signup)).setAnchorView(binding.signInLoginBtn)
                        .show()
                }
                //회원가입 취소
                else {
                    binding.root.makeSnackBar(getString(R.string.signin_fail_signup)).setAnchorView(binding.signInLoginBtn)
                        .show()
                }
            }
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun showPw() {
        Log.d("Signup","showPw touch 상태 ${binding.signInPwEt.inputType}")
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
            if (binding.signInIdEt.text.toString() == idFromSignup &&
                binding.signInPwEt.text.toString() == (pwFromSignup)
            ) {
                this.showToast("로그인에 성공했습니다")

                //로그인 성공 시 id,pw,mbti를 sp에 저장
                editor.putString("id",idFromSignup)
                editor.putString("pw",pwFromSignup)
                editor.putString("mbti",mbtiFromSignup)
                editor.commit()

                val homeIntent = Intent(this, HomeActivity::class.java)
                homeIntent.putExtra("id", idFromSignup)
                homeIntent.putExtra("pw", pwFromSignup)
                homeIntent.putExtra("mbti", mbtiFromSignup)
                homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(homeIntent)
            }
            //로그인 실패
            else {

                binding.root.makeSnackBar(getString(R.string.signin_fail_id_or_pw)).setAnchorView(binding.signInLoginBtn)
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
    //자동 로그인
    private fun autoLogin(){
        val intent:Intent = Intent(this, HomeActivity::class.java)
        //로그인 성공 했을 때 저장해놓은 정보를 HomeActivity에 보내면서 자동 로그인
        val id = ApplicationClass.sSharedPreferences.getString("id",null)
        val pw = ApplicationClass.sSharedPreferences.getString("pw",null)
        val mbti = ApplicationClass.sSharedPreferences.getString("mbti",null)
        if(id!=null && pw!=null){
            intent.putExtra("id",id)
            intent.putExtra("pw",pw)
            intent.putExtra("mbti",mbti)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.showToast("자동 로그인 되었습니다.")
            startActivity(intent)
        }
    }
}