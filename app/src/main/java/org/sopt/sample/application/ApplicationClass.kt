package org.sopt.sample.application

import android.app.Application
import android.content.SharedPreferences
import org.sopt.sample.R
//앱 최초 생성 시 sp 새로 생성
class ApplicationClass: Application() {
    //전역변수

    companion object{
        //상수화 -> 가독성 및 유지보수
        const val USER_INFO_ID = "id"
        const val USER_INFO_PW = "pw"
        const val USER_INFO_MBTI = "mbti"
        lateinit var sSharedPreferences: SharedPreferences
    }
    override fun onCreate() {
        super.onCreate()
        sSharedPreferences = applicationContext.getSharedPreferences("${R.string.login_info_sp}", MODE_PRIVATE)
    }
}