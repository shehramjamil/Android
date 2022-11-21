package com.android.navigator

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.core.BaseActivity
import com.android.core.intents.NavigationComponentActivityIntent

class NavigatorActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.activity_navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LazyColumn {
                items(listOfFeatureModules) { item ->
                    FeatureButton(item)
                }
            }
        }
    }

    @Composable
    private fun FeatureButton(item: String) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            onClick = { onFeatureClick(item) },
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(text = item)
        }
    }

    private fun onFeatureClick(featureName: String) {
        startActivity(NavigationComponentActivityIntent.createIntent(this))
    }

    companion object {
        private val listOfFeatureModules =
            listOf("Clean Architecture Implementation", "Retrofit Implementation")
    }
}
