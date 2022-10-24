package uz.gita.eventapp.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import uz.gita.eventapp.data.room.entity.EventData

interface MainViewModel {

    val allSwitchedEvents: SharedFlow<List<EventData>>
    val allEvents: SharedFlow<List<EventData>>

    fun openSettingScreen()

    fun changeState(event: EventData)
}