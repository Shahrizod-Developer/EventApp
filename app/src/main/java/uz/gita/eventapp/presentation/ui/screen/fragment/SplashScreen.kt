package uz.gita.eventapp.presentation.ui.screen.fragment

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore.Images.Thumbnails.IMAGE_ID
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.eventapp.R
import uz.gita.eventapp.databinding.ScreenSplashBinding
import uz.gita.eventapp.presentation.ui.viewmodel.SplashScreenViewModel
import uz.gita.eventapp.presentation.ui.viewmodel.impl.SplashScreenViewModelImpl


@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {

    private val binding: ScreenSplashBinding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve", "MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.openMainScreen()



        viewModel.click.observe(this) {
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
        }
    }
}