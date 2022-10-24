package uz.gita.eventapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.gita.eventapp.data.shp.MySharedPreference

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreference.init(this)
    }
}