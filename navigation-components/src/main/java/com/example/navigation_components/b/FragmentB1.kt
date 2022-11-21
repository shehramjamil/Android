package com.example.navigation_components.b

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.navigation_components.R
import com.example.navigation_components.databinding.FragmentABinding

class FragmentB1 : Fragment(R.layout.fragment_b1) {

    private val fragmentBinding: FragmentABinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}