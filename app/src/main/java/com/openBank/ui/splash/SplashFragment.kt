package com.openBank.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.openBank.databinding.SplashFragmentBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashFragment : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSplash()
    }

    private fun startSplash() {
        launch {
            delay(5000)
            withContext(Dispatchers.Main) {
                val action = SplashFragmentDirections.actionSplashFragmentToCharacterFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }
        }

    }
}