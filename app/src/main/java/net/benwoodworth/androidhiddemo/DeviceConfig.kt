package net.benwoodworth.androidhiddemo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.RuntimeException

private const val ARG_DEVICE_NAME = "deviceName"
private const val ARG_DEVICE_PATH = "devicePath"
private const val ARG_USE_ROOT = "useRoot"

class DeviceConfig : Fragment() {
    private lateinit var deviceName: String
    private lateinit var devicePath: String
    private var useRoot: Boolean = false

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.run {
            deviceName = getString(ARG_DEVICE_NAME)!!
            devicePath = getString(ARG_DEVICE_PATH)!!
            useRoot = getBoolean(ARG_USE_ROOT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_config, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? OnFragmentInteractionListener
            ?: throw RuntimeException("$context must implement OnFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(deviceName: String, devicePath: String, useRoot: Boolean): DeviceConfig {
            return DeviceConfig().apply {
                arguments = Bundle().apply {
                    putString(ARG_DEVICE_NAME, deviceName)
                    putString(ARG_DEVICE_PATH, devicePath)
                    putBoolean(ARG_USE_ROOT, useRoot)
                }
            }
        }
    }
}
