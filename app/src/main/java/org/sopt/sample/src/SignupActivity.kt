package org.sopt.sample.src

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, SignInActivity::class.java)

        binding.signUpCompleteBtn.setOnClickListener {
            //ID CHECK - Length Success
            if (binding.signUpIdEt.text.length in 6..10) {
                //PW CHECK - Length Success
                if (binding.signUpPwEt.text.length in 8..12) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.signup_login_success),
                        Snackbar.LENGTH_SHORT,
                    ).show()
                    //SignUp Success->HomeActivity
                    startActivity(intent)
                }
                //PW CHECK - Length Fail
                else {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.signup_fail_pw_length),
                        Snackbar.LENGTH_SHORT
                    ).setAnchorView(binding.signUpPwEt).show()
                }
            }
            //ID CHECK - Length Fail
            else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.signup_fail_id_length),
                    Snackbar.LENGTH_SHORT
                ).setAnchorView(binding.signUpIdEt).show()
            }
        }
    }
}