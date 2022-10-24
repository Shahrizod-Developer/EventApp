package uz.gita.eventapp.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.eventapp.presentation.navigation.Navigator
import uz.gita.eventapp.presentation.ui.screen.fragment.SplashScreenDirections
import uz.gita.eventapp.presentation.ui.viewmodel.SplashScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl  @Inject constructor(
    private val navigator: Navigator
):SplashScreenViewModel, ViewModel(){
    override val click = MutableLiveData<Unit>()

    override fun openMainScreen() {
        viewModelScope.launch {
            delay(2000)
            click.value = Unit
        }
    }
}