package net.benwoodworth.androidhiddemo


import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_gamepad.*
import net.benwoodworth.androidhiddemo.hid.HidGamePad

class GamePad : Fragment() {

    private lateinit var hidGadgets: HidGadgets

    companion object {
        fun newInstance(hidGadgets: HidGadgets) = GamePad().apply {
            this.hidGadgets = hidGadgets
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gamepad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button00.hookGamePad { button00 = it }
        button01.hookGamePad { button01 = it }
        button02.hookGamePad { button02 = it }
        button03.hookGamePad { button03 = it }
        button04.hookGamePad { button04 = it }
        button05.hookGamePad { button05 = it }
        button06.hookGamePad { button06 = it }
        button07.hookGamePad { button07 = it }
        button08.hookGamePad { button08 = it }
    }

    private fun Button.hookGamePad(setButton: HidGamePad.State.(pressed: Boolean) -> Unit) {
        onPressOrRelease { pressed ->
            hidGadgets.gamePad.value?.updateState {
                setButton(pressed)
            }

            if (pressed) {
                vibrate(150)
            }
        }
    }

    private fun Button.onPressOrRelease(action: (press: Boolean) -> Unit) {
        setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> action(true)
                MotionEvent.ACTION_UP -> action(false)
                else -> return@setOnTouchListener false
            }

            return@setOnTouchListener true
        }
    }

    private fun vibrate(durationMs: Long) {
        if (Build.VERSION.SDK_INT >= 26) {
            (context?.getSystemService(VIBRATOR_SERVICE) as Vibrator)
                .vibrate(
                    VibrationEffect.createOneShot(
                        durationMs,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
        } else {
            @Suppress("DEPRECATION")
            (context?.getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(durationMs)
        }
    }
}
