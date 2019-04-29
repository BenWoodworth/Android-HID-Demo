package net.benwoodworth.androidhiddemo.hid

import net.benwoodworth.androidhiddemo.hid.HidConnection
import net.benwoodworth.androidhiddemo.hid.HidKeyboardDsl
import java.io.Closeable

class HidKeyboard(
    private val connection: HidConnection
) : Closeable by connection {

    companion object {
        const val MOD_LCTRL: Int = 1 shl 0
        const val MOD_LSHIFT: Int = 1 shl 1
        const val MOD_LALT: Int = 1 shl 2
        const val MOD_LSUPER: Int = 1 shl 3
        const val MOD_RCTRL: Int = 1 shl 4
        const val MOD_RSHIFT: Int = 1 shl 5
        const val MOD_RALT: Int = 1 shl 6
        const val MOD_RSUPER: Int = 1 shl 7
    }

    fun setState(
        modifiers: Int = 0,
        key0: Int = 0,
        key1: Int = 0,
        key2: Int = 0,
        key3: Int = 0,
        key4: Int = 0,
        key5: Int = 0
    ) {
        connection.write(
            modifiers.toByte(),
            0,
            key0.toByte(),
            key1.toByte(),
            key2.toByte(),
            key3.toByte(),
            key4.toByte(),
            key5.toByte()
        )
    }

    inline operator fun invoke(dsl: HidKeyboardDsl.() -> Unit) {
        dsl(HidKeyboardDsl(this, 0))
        setState()
    }
}