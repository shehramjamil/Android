package com.example.navigation_components

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.android.core.BaseActivity
import com.android.core.intents.NavigationComponentActivityIntent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class NavigationComponentsActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navigationController: NavController

    override val layoutId: Int
        get() = R.layout.activity_navigation_components

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getNavController()

        passInitialBundleToStartDestination()

        setUpToolbar()

        setUpNavigationBar()

        setUpBottomNavigationBar()
    }

    private fun getNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigationController = navHostFragment.navController
    }

    private fun setUpToolbar() {
        appBarConfiguration = AppBarConfiguration(
            navGraph = navigationController.graph,
            drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        )

        findViewById<Toolbar>(R.id.toolbar).apply {
            setupWithNavController(
                navigationController,
                appBarConfiguration
            )
            // Here we can access further settings of toolbar
        }
    }

    private fun setUpNavigationBar() {
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navigationController)
    }

    private fun setUpBottomNavigationBar() {
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navigationController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // To navigate to the clicked menu destination either bottom navigation items, navigation drawer items, ot toolbar menu items
        return item.onNavDestinationSelected(navigationController) || super.onOptionsItemSelected(
            item
        )
    }

    private fun passInitialBundleToStartDestination() {
        navigationController.setGraph(
            R.navigation.main_navigation,
            bundleOf(NavigationComponentActivityIntent.INITIAL_BUNDLE_KEY to "Initial Bundle From Activity")
        )
    }

    /**
     * Following is always used when the activity launch mode in manifest is singleTop
     * singleTop does not create the activity again, instead reused the already existing instance
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigationController.handleDeepLink(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // can be used on the exiting activity or some operations can be done before exiting the app
        /** if (!navigationController.popBackStack()) {
        finish()
        } */
    }
}


// Important
// 1. use @keep to save the parcelable, serializable and enum args - See documentation (Pass data between destinations)
// 2. Dialogue destinations appear over the top of a non floating destination.
//    when navigation from a dialogue to a non floating destination, all dialogue destinations are popped from the top of back stack
//    see Documentation - navigate to a destination
// 3. https://developer.android.com/guide/navigation/navigation-navigate#savestate also see popUpToSaveState and popupToRestoreState