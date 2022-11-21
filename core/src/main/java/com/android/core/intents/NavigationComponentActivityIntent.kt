package com.android.core.intents

import android.content.Context
import android.content.Intent

object NavigationComponentActivityIntent {

    private const val NAVIGATION_COMPONENT_ACTION = "activity.navigation.component.open"

    fun createIntent(context: Context): Intent {
        return Intent().apply {
            action = NAVIGATION_COMPONENT_ACTION
            setPackage(context.packageName)
        }
    }

    const val INITIAL_BUNDLE_KEY = "initial_bundle_key"
}