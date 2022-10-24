package uz.gita.eventapp.broadcast

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.speech.tts.TextToSpeech
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.eventapp.data.room.dao.EventDao
import uz.gita.eventapp.domain.repository.AppRepository
import uz.gita.eventapp.service.EventService
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EventBroadcast : BroadcastReceiver() {

    var tts: TextToSpeech? = null

    @Inject
    lateinit var dao: EventDao

    override fun onReceive(context: Context?, intent: Intent?) {
        if (tts == null) {
            tts = TextToSpeech(context) { status ->
                if (status != TextToSpeech.ERROR) {
                    tts!!.language = Locale.US
                }
            }
        }
        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> {
                event(1)
            }
            Intent.ACTION_SCREEN_OFF -> {
                event(2)
            }
            WifiManager.NETWORK_STATE_CHANGED_ACTION -> {
                val action = intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
                if (action?.isConnected!!) {
                    event(3)
                } else {
                    event(4)
                }
            }
            Intent.ACTION_POWER_CONNECTED -> {
                event(15)
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                event(16)
            }

            Intent.ACTION_TIME_CHANGED -> {
                event(11)
            }
            Intent.ACTION_BATTERY_LOW -> {
                event(14)
            }
            Intent.ACTION_BATTERY_OKAY -> {
                event(13)
            }
            Intent.ACTION_SHUTDOWN -> {
                event(12)
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                if (isAirplaneModeOn) {
                    event(9)
                } else {
                    event(10)
                }
            }
            Intent.ACTION_HEADSET_PLUG -> {
                val state = intent.getIntExtra("state", -1)
                if (state == 0) {
                    event(8)
                } else if (state == 1) {
                    event(7)

                }
            }
            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                if (state == BluetoothAdapter.STATE_OFF) {
                    event(6)
                } else if (state == BluetoothAdapter.STATE_ON) {
                    event(5)
                }
            }
            Intent.ACTION_REBOOT -> {
                context?.startService(Intent(context, EventService::class.java))
            }
        }
    }

    private fun event(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = dao.getEventGetById(id)
            if (data.state == 1) {
                speak(data.name)
            }
        }
    }

    private fun speak(text: String) {
        tts?.speak("$text", TextToSpeech.QUEUE_FLUSH, null, "This is screen on")
    }
}