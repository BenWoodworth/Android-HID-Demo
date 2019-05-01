package net.benwoodworth.androidhiddemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.benwoodworth.androidhiddemo.hid.HidGamePad
import net.benwoodworth.androidhiddemo.hid.HidKeyboard

class HidGadgets : ViewModel() {

    val keyboard = MutableLiveData<HidKeyboard>()

    val gamePad = MutableLiveData<HidGamePad>()

    override fun onCleared() {
        super.onCleared()

        keyboard.value?.close()
        keyboard.value = null

        gamePad.value?.close()
        gamePad.value = null
    }
}