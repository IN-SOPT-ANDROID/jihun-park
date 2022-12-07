package org.sopt.sample.application

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp
import org.sopt.sample.BuildConfig
import timber.log.Timber
@HiltAndroidApp
class ApplicationClass : Application() {
    companion object{
        private lateinit var applicationClass: ApplicationClass
        fun getInstance(): ApplicationClass = applicationClass
        val networkFlipperPlugin = NetworkFlipperPlugin()
    }

    override fun onCreate() {
        super.onCreate()
        initFlipper()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }


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