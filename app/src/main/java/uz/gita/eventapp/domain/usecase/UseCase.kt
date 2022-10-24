package uz.gita.eventapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.eventapp.data.room.entity.EventData

interface UseCase {
    suspend fun insertEvent(event: EventData)

    suspend fun changeState(event: EventData)

    fun getAllEvent(): Flow<List<EventData>>

    fun getAllSwitchEvent(): Flow<List<EventData>>
}