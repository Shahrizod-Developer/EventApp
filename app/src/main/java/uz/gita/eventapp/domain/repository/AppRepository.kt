package uz.gita.eventapp.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.eventapp.data.room.entity.EventData

interface AppRepository {

    fun insertEvent(event: EventData)

    fun changeState(event: EventData)

    fun getAllEvent(): Flow<List<EventData>>

    fun getAllSwitchEvent(): Flow<List<EventData>>
}