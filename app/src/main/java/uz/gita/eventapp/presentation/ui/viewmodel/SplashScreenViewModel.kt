package uz.gita.eventapp.presentation.ui.viewmodel

import androidx.lifecycle.LiveData

interface SplashScreenViewModel {

    val click:LiveData<Unit>
    fun openMainScreen()
}