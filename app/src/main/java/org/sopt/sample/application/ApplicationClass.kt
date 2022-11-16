package org.sopt.sample.application

import android.app.Application
import org.sopt.sample.util.datastore.UserManager

class ApplicationClass : Application() {
    private lateinit var userManager: UserManager

    companion object{
        private lateinit var applicationClass: ApplicationClass
        fun getInstance(): ApplicationClass = applicationClass
    }

    override fun onCreate() {
        super.onCreate()
        userManager = UserManager(this)
    }
    fun getUserManager(): UserManager = userManager //DataStore 싱글톤으로 사용하기
}