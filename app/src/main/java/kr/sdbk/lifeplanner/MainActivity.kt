package kr.sdbk.lifeplanner

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.base.BaseActivity
import kr.sdbk.lifeplanner.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate
) {
    override val activityViewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun afterBinding() {
        setNavigation()
        setBottomNavigation()
    }

    override fun observeViewModel() {

    }

    private fun setNavigation() {
        val navHost = supportFragmentManager.findFragmentById(R.id.main_nav_container) as NavHostFragment
        navController = navHost.navController
        val graph = navController.navInflater.inflate(R.navigation.main_nav)
        graph.setStartDestination(kr.sdbk.feature_splash.R.id.splash_nav)
        navController.graph = graph
    }

    private fun setBottomNavigation() {
        binding.mainBottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, arg ->
            val isBottomNavigationVisible = when {
                arg is Bundle && !arg.isEmpty -> arg.getBoolean(getString(kr.sdbk.core_common.R.string.bottom_navigation_visible))
                else -> false
            }
            setBottomNavigationVisible(isBottomNavigationVisible)
        }
    }

    private fun setBottomNavigationVisible(isVisible: Boolean) {
        binding.mainBottomNav.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}