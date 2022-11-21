package com.example.navigation_components

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.navigation_components.databinding.GeneralFragmentBinding

class GeneralFragment : Fragment(R.layout.general_fragment) {

    private val fragmentBinding: GeneralFragmentBinding by viewBinding()

    private val args: GeneralFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentBinding.textView1.text = args.generalString
        fragmentBinding.textView2.text = args.generaLInteger.toString()
        fragmentBinding.textView3.text = args.intArray?.count().toString()


        // In case we have to send some data back to previous fragment
        val savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle[DATA_FROM_GENERAL_FRAGMENT] = "Data from general fragment"
    }

    companion object {
        const val DATA_FROM_GENERAL_FRAGMENT: String = "DATA_FROM_GENERAL_FRAGMENT"
    }
}