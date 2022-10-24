package uz.gita.eventapp.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.eventapp.data.room.entity.EventData
import uz.gita.eventapp.domain.usecase.UseCase
import uz.gita.eventapp.presentation.navigation.Navigator
import uz.gita.eventapp.presentation.ui.screen.fragment.MainScreenDirections
import uz.gita.eventapp.presentation.ui.viewmodel.MainViewModel
import javax.inject.Inject

private fun <T> eventFlow() =
    MutableSharedFlow<T>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val useCase: UseCase
) : MainViewModel, ViewModel() {

    override val allSwitchedEvents = eventFlow<List<EventData>>()

    override val allEvents = eventFlow<List<EventData>>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllSwitchEvent().collectLatest {
                allSwitchedEvents.emit(it)
            }
        }
        viewModelScope.launch {
            useCase.getAllEvent().collectLatest {
                allEvents.emit(it)
            }
        }
    }

    override fun openSettingScreen() {
        viewModelScope.launch {
            navigator.navigateTo(MainScreenDirections.actionMainScreenToSettingScreen())
        }
    }

    override fun changeState(event: EventData) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.changeState(event)
        }
    }
}