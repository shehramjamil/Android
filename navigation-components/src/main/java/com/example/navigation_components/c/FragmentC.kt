package com.example.navigation_components.c

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.navigation_components.R
import com.example.navigation_components.databinding.FragmentABinding

class FragmentC : Fragment(R.layout.fragment_c) {

    private val fragmentBinding: FragmentABinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}