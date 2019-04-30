package net.benwoodworth.androidhiddemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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

}
