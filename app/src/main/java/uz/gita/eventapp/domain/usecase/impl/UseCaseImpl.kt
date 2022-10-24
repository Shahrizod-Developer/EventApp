package uz.gita.eventapp.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.eventapp.data.room.entity.EventData
import uz.gita.eventapp.domain.repository.AppRepository
import uz.gita.eventapp.domain.usecase.UseCase
import javax.inject.Inject

class UseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : UseCase {

    override suspend fun insertEvent(event: EventData) = repository.insertEvent(event)

    override suspend fun changeState(event: EventData) = repository.changeState(event)

    override fun getAllEvent(): Flow<List<EventData>> = repository.getAllEvent()

    override fun getAllSwitchEvent(): Flow<List<EventData>> = repository.getAllSwitchEvent()

}