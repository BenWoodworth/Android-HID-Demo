package net.benwoodworth.androidhiddemo.hid

import java.io.Closeable
import kotlin.properties.Delegates

class HidGamePad(
    private val connection: HidConnection
) : Closeable by connection {

    private val state = State()

    fun updateState(update: State.() -> Unit) {
        update(state)

        with(state) {
            connection.write(
                bits(
                    button08,
                    button07,
                    button06,
                    button05,
                    button04,
                    button03,
                    button02,
                    button01
                ).toByte(),
                bits(
                    button16,
                    button15,
                    button14,
                    button13,
                    button12,
                    button11,
                    button10,
                    button09
                ).toByte(),
                joy1X.toByte(),
                joy1Y.toByte(),
                joy2X.toByte(),
                joy2Y.toByte()
            )
        }
    }

    private fun bits(vararg bits: Boolean): Int {
        return bits.fold(0) { acc: Int, b: Boolean ->
            (acc shl 1) or (if (b) 1 else 0)
        }
    }

    @HidDslMarker
    inner class State {
        private fun joyDelegate() = Delegates.observable(0) { prop, _, new ->
            assert(new in -127..127) {
                "Value of ${prop.name} must be in the range -127..127"
            }
        }

        var joy1X: Int by joyDelegate()
        var joy1Y: Int by joyDelegate()

        var joy2X: Int by joyDelegate()
        var joy2Y: Int by joyDelegate()

        var button01: Boolean = false
        var button02: Boolean = false
        var button03: Boolean = false
        var button04: Boolean = false
        var button05: Boolean = false
        var button06: Boolean = false
        var button07: Boolean = false
        var button08: Boolean = false
        var button09: Boolean = false
        var button10: Boolean = false
        var button11: Boolean = false
        var button12: Boolean = false
        var button13: Boolean = false
        var button14: Boolean = false
        var button15: Boolean = false
        var button16: Boolean = false
    }
}
