package com.example.navigation_components.a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.core.intents.NavigationComponentActivityIntent
import com.example.navigation_components.GeneralFragment
import com.example.navigation_components.R
import com.example.navigation_components.databinding.FragmentABinding

class FragmentA : Fragment(R.layout.fragment_a) {

    private val fragmentBinding: FragmentABinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromGeneralFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getInitialBundle()
        setClickListener()
    }

    private fun getInitialBundle() {
        val initialBundleFromContainerActivity =
            arguments?.getString(NavigationComponentActivityIntent.INITIAL_BUNDLE_KEY)
        fragmentBinding.textView.text = initialBundleFromContainerActivity
    }

    private fun setClickListener() {
        with(fragmentBinding) {
            button1.setOnClickListener {
                val actionFragmentAToGeneralWithSafeArguments =
                    FragmentADirections.actionFragmentAToGeneralFragment(
                        generalString = "Safe argument string",
                        generaLInteger = 1,
                        intArray = intArrayOf(1, 2, 3)
                    )
                /**
                 * navOptions override xml options
                 */
                findNavController().navigate(actionFragmentAToGeneralWithSafeArguments, navOptions {
                    anim {
                        enter = android.R.animator.fade_in
                        exit = android.R.animator.fade_out
                    }
                })
            }

            button2.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentA_to_dialogueFragment)
            }

            button3.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentA_to_navigation_b)
            }

            button4.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentA_to_navigation_c)
            }

            button5.setOnClickListener {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("http://www.example.com/users/1?arg2=2".toUri())
                    .build()
                findNavController().navigate(request)
            }

            button6.setOnClickListener {
                startActivity(NavigationComponentActivityIntent.createIntent(requireContext()))
            }
        }
    }

    private fun getDataFromGeneralFragment() {
        val currentBackStackEntry = findNavController().currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle
        savedStateHandle.getLiveData<String>(GeneralFragment.DATA_FROM_GENERAL_FRAGMENT)
            .observe(currentBackStackEntry, Observer { success ->
                Log.d(success.toString(), success.toString())
            })
    }
}