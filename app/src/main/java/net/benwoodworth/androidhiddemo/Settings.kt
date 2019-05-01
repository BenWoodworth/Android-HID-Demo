package net.benwoodworth.androidhiddemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import net.benwoodworth.androidhiddemo.hid.HidGamePad
import net.benwoodworth.androidhiddemo.hid.HidKeyboard


class Settings : Fragment() {

    private lateinit var appGadgets: HidGadgets

    companion object {
        fun newInstance(appGadgets: HidGadgets) = Settings().apply {
            this.appGadgets = appGadgets
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val keyboardConfig = childFragmentManager
            .findFragmentById(R.id.keyboardConfig) as GadgetConfig

        val gamePadConfig = childFragmentManager
            .findFragmentById(R.id.gamePadConfig) as GadgetConfig

        keyboardConfig.gadget.apply {
            name.value = "Keyboard"
            defaultPath.value = "/dev/hidg0"

            connection.observe(this@Settings, Observer { connection ->
                appGadgets.keyboard.value = connection
                    ?.let { HidKeyboard(it) }
            })
        }

        gamePadConfig.gadget.apply {
            name.value = "Game Pad"
            defaultPath.value = "/dev/hidg1"

            connection.observe(this@Settings, Observer { connection ->
                appGadgets.gamePad.value = connection
                    ?.let { HidGamePad(it) }
            })
        }
    }
}
