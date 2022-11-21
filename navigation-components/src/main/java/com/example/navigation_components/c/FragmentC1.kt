package com.example.navigation_components.c

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.navigation_components.R
import com.example.navigation_components.databinding.FragmentC1Binding

class FragmentC1 : Fragment(R.layout.fragment_c1) {

    private val fragmentBinding: FragmentC1Binding by viewBinding()

    private val arguments : FragmentC1Args by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentBinding.textView.text = "Arguments from deeplink = ".plus(arguments.arg1.toString().plus(arguments.arg2.toString()))
    }
}