package org.sopt.sample.config

import android.app.Application
import android.content.SharedPreferences
import org.sopt.sample.R
//앱 최초 생성 시 sp 새로 생성
class ApplicationClass: Application() {
    //전역변수
    companion object{
        lateinit var sSharedPreferences: SharedPreferences
    }
    override fun onCreate() {
        super.onCreate()
        sSharedPreferences = applicationContext.getSharedPreferences("${R.string.login_info_sp}", MODE_PRIVATE)
    }
}