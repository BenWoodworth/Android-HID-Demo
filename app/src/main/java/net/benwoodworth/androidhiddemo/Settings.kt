package net.benwoodworth.androidhiddemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders


class Settings : Fragment() {

    private lateinit var keyboardConfig: GadgetConfig
    private lateinit var gamePadConfig: GadgetConfig

    companion object {
        fun newInstance() = Settings()
    }

    lateinit var viewModel: SettingsViewModel
        private set

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keyboardConfig = childFragmentManager.findFragmentById(R.id.keyboardConfig) as GadgetConfig
        gamePadConfig = childFragmentManager.findFragmentById(R.id.gamePadConfig) as GadgetConfig

        keyboardConfig.viewModel.apply {
            name.value = "Keyboard"
            defaultPath.value = "/dev/hidg0"
        }

        gamePadConfig.viewModel.apply {
            name.value = "Game Pad"
            defaultPath.value = "/dev/hidg1"
        }
    }
}
