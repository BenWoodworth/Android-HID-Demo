package net.benwoodworth.androidhiddemo

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_gadget_config.*
import net.benwoodworth.androidhiddemo.databinding.FragmentGadgetConfigBinding

class GadgetConfig : Fragment() {

    companion object {
        fun newInstance() = GadgetConfig()
    }

    lateinit var viewModel: GadgetConfigViewModel
        private set

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(GadgetConfigViewModel::class.java)

        val binding = FragmentGadgetConfigBinding.inflate(inflater)
        binding.gadget = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val devicePathType = textDevicePath.inputType
        val devicePathBackground = textDevicePath.background

        // TODO Use data binding
        viewModel.isEditable.observe(this, Observer { editable ->
            if (editable) {
                textDevicePath.inputType = devicePathType
                textDevicePath.background = devicePathBackground
            } else {
                textDevicePath.inputType = InputType.TYPE_NULL
                textDevicePath.background = null
            }

            switchUseRoot.isEnabled = editable
        })
    }
}
