package org.sopt.sample.application

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import org.sopt.sample.BuildConfig
import org.sopt.sample.util.datastore.UserManager
import timber.log.Timber

class ApplicationClass : Application() {
    private lateinit var userManager: UserManager

    companion object{
        private lateinit var applicationClass: ApplicationClass
        fun getInstance(): ApplicationClass = applicationClass
        val networkFlipperPlugin = NetworkFlipperPlugin()
    }

    override fun onCreate() {
        super.onCreate()
        userManager = UserManager(this)
        initFlipper()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
    fun getUserManager(): UserManager = userManager //DataStore 싱글톤으로 사용하기

    private fun initFlipper() {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).apply {
                addPlugin(InspectorFlipperPlugin(this@ApplicationClass, DescriptorMapping.withDefaults()))
                addPlugin(networkFlipperPlugin)
                addPlugin(LeakCanary2FlipperPlugin()) //-> 디버그에서 메모리 릭 잡아주는 친구
                // addPlugin(SharedPreferencesFlipperPlugin(app, "SOPT_DATA")) -> 해당 키를 가진 SharedPreference 내부 데이터를 볼 수 있는 친구
            }.start()
        }
    }
}