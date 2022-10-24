package uz.gita.eventapp.domain.repository.impl

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.eventapp.data.room.dao.EventDao
import uz.gita.eventapp.data.room.entity.EventData
import uz.gita.eventapp.data.shp.MySharedPreference
import uz.gita.eventapp.domain.repository.AppRepository
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(
    private val dao: EventDao
) : AppRepository {
    val data = MySharedPreference.getInstance()

    init {


        if (!data.internet) {
            dao.insert(
                listOf(
                    EventData(0, "Screen on", 1, 0),
                    EventData(0, "Screen off", 2, 0),
                    EventData(0, "Wifi connected", 3, 0),
                    EventData(0, "Wifi disconnected", 4, 0),
                    EventData(0, "Bluetooth on", 5, 0),
                    EventData(0, "Bluetooth off", 6, 0),
                    EventData(0, "HeadPhones on", 7, 0),
                    EventData(0, "HeadPhones off", 8, 0),
                    EventData(0, "Plane on", 9, 0),
                    EventData(0, "Plane off", 10, 0),
                    EventData(0, "Changed time", 11, 0),
                    EventData(0, "Shut down", 12, 0),
                    EventData(0, "Full battery", 13, 0),
                    EventData(0, "Low battery", 14, 0),
                    EventData(0, "Power on", 15, 0),
                    EventData(0, "Power off", 16, 0)
                )
            )
            MySharedPreference.getInstance().internet = true
        }
    }

    override fun insertEvent(event: EventData) = dao.insert(event)

    override fun changeState(event: EventData) = dao.changeEventState(event)

    override fun getAllEvent(): Flow<List<EventData>> = dao.getAllEvent()
    override fun getAllSwitchEvent(): Flow<List<EventData>> = dao.getAllSwitchEvent()
}