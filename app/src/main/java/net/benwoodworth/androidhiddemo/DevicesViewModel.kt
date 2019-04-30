package net.benwoodworth.androidhiddemo

import androidx.lifecycle.ViewModel;

class DevicesViewModel : ViewModel() {

    var keyboard: Device = Device("/dev/hidg0", false, false)

    var gamepad: Device = Device("/dev/hidg1", false, false)

    inner class Device(
        var path: String,
        var useRoot: Boolean,
        var connected: Boolean
    )
}
