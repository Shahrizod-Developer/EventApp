package uz.gita.eventapp.data.shp

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MySharedPreference @Inject constructor(@ApplicationContext context: Context) {

    var internet: Boolean
        get() = sharedPreferences.getBoolean("internet", false)
        set(internet) {
            editor.putBoolean("internet", internet).apply()
        }

    var wiFi: Boolean
        get() = sharedPreferences.getBoolean("wi_fi", false)
        set(wiFi) {
            editor.putBoolean("wi_fi", wiFi).apply()
        }

    var hotspot: Boolean
        get() = sharedPreferences.getBoolean("hotspot", false)
        set(hotspot) {
            editor.putBoolean("hotspot", hotspot).apply()
        }

    var screen: Boolean
        get() = sharedPreferences.getBoolean("screen", false)
        set(screen) {
            editor.putBoolean("screen", screen).apply()
        }

    var bluetooth: Boolean
        get() = sharedPreferences.getBoolean("bluetooth", false)
        set(bluetooth) {
            editor.putBoolean("bluetooth", bluetooth).apply()
        }

    var charging: Boolean
        get() = sharedPreferences.getBoolean("charging", false)
        set(charging) {
            editor.putBoolean("charging", charging).apply()
        }


    companion object {
        var mySharedPreference: MySharedPreference? = null
        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor

        fun init(context: Context): MySharedPreference? {
            if (mySharedPreference == null) {
                mySharedPreference = MySharedPreference(context)
            }
            return mySharedPreference
        }

        fun getInstance() = mySharedPreference!!
    }

    init {
        sharedPreferences = context.getSharedPreferences("app_name", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }
}
