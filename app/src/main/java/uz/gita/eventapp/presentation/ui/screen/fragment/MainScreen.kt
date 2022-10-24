package uz.gita.eventapp.presentation.ui.screen.fragment

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.eventapp.R
import uz.gita.eventapp.databinding.DialogInfoBinding
import uz.gita.eventapp.databinding.ScreenMainBinding
import uz.gita.eventapp.databinding.ScreenSplashBinding
import uz.gita.eventapp.presentation.adapter.EventAdapter
import uz.gita.eventapp.presentation.ui.viewmodel.MainViewModel
import uz.gita.eventapp.presentation.ui.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding: ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapter: EventAdapter by lazy { EventAdapter(true) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allSwitchedEvents.collectLatest {
                Log.d("TTT", it.toString())
                if (it.isNotEmpty()) {
                    binding.card.visibility = View.VISIBLE
                    binding.text.visibility = View.GONE
                    adapter.submitList(it)
                } else {
                    binding.card.visibility = View.GONE
                    binding.text.visibility = View.VISIBLE
                }
            }
        }

        binding.share.setOnClickListener {
            ShareCompat.IntentBuilder.from(requireActivity())
                .setType("text/plain")
                .setChooserTitle("Chooser title")
                .setText("http://play.google.com/store/apps/details?id=" + activity?.packageName)
                .startChooser();
        }

        binding.info.setOnClickListener {
            showInfo()
        }

        binding.rv.adapter = adapter

        binding.setting.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToSettingScreen())
        }
    }

    private fun showInfo() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            DialogInfoBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialogBinding.enter.setOnClickListener {
            dialog.cancel()
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setView(dialogBinding.root)
        dialog.show()
    }

}