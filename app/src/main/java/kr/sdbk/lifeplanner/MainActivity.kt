package kr.sdbk.lifeplanner

import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.base.BaseActivity
import kr.sdbk.lifeplanner.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate
) {
    override val activityViewModel: MainViewModel by viewModels()

    override fun afterBinding() {
        setNavigation()
    }

    override fun observeViewModel() {

    }

    private fun setNavigation() {
        val navHost = supportFragmentManager.findFragmentById(R.id.main_nav_container) as NavHostFragment
        val navController = navHost.navController
        val graph = navController.navInflater.inflate(kr.sdbk.core_common.R.navigation.main_nav)
        graph.setStartDestination(kr.sdbk.feature_splash.R.id.splash_nav)
        navController.graph = graph
    }
}