package net.benwoodworth.androidhiddemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import net.benwoodworth.androidhiddemo.hid.HidConnection

class GadgetConfigViewModel : ViewModel() {

    val connection: LiveData<HidConnection?> = PrivateMutableLiveData()

    val isConnected = MutableLiveData<Boolean>().apply {
        value = false

        observeForever { connect ->
            connection as PrivateMutableLiveData
            val connectionValue = connection.value

            if (connect && connectionValue == null) {
                // Connect
                val devicePath = path.value
                    .takeUnless { it.isNullOrEmpty() }
                    ?: defaultPath.value

                val newConnection = HidConnection(devicePath!!, useRoot.value!!)
                connection.setValue(newConnection)
            } else if (!connect && connectionValue != null) {
                // Disconnect
                connectionValue.close()
                connection.setValue(null)
            }
        }
    }

    val isEditable = Transformations.map(isConnected) { isConnected ->
        !isConnected
    }

    val name = MutableLiveData<String>().apply {
        value = "Gadget"
    }

    val path = MutableLiveData<String>()

    val defaultPath = MutableLiveData<String>().apply {
        value = "/dev/hidg0"
    }

    val useRoot = MutableLiveData<Boolean>().apply {
        value = false
    }

    override fun onCleared() {
        isConnected.value = false
    }

    private inner class PrivateMutableLiveData<T> : LiveData<T>() {
        public override fun setValue(value: T) = super.setValue(value)
    }
}
