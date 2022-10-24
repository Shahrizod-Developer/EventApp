package uz.gita.eventapp.presentation.ui.screen.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import uz.gita.eventapp.databinding.ScreenWorkingBinding
import uz.gita.eventapp.presentation.adapter.EventAdapter
import uz.gita.eventapp.presentation.ui.viewmodel.MainViewModel
import uz.gita.eventapp.presentation.ui.viewmodel.impl.MainViewModelImpl
import uz.gita.eventapp.service.EventService

@AndroidEntryPoint
class SettingScreen : Fragment(R.layout.screen_working) {

    private val binding: ScreenWorkingBinding by viewBinding(ScreenWorkingBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapter: EventAdapter by lazy { EventAdapter(false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lifecycleScope.launch {
            viewModel.allEvents.collectLatest {
                adapter.submitList(it)
            }
        }
        binding.rv.adapter = adapter

        adapter.setSwitchChangedListener {
            viewModel.changeState(it)
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        val intent = Intent(requireContext(), EventService::class.java)
        requireActivity().startService(intent)


    }
}