package kr.sdbk.core_common.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import java.util.ArrayDeque
import java.util.Deque

@Navigator.Name("bottom_navigator")
class BottomNavigator(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int,
): Navigator<FragmentNavigator.Destination>() {
    private val backStack: Deque<String> = ArrayDeque()

    override fun createDestination(): FragmentNavigator.Destination = FragmentNavigator.Destination(this)

    override fun navigate(
        destination: FragmentNavigator.Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {
        val className = destination.className
        val tag = className.split('.').last()

        if (backStack.peekLast() == tag) {
            return null
        }

        if (backStack.peekLast() != tag) {
            backStack.addLast(tag)
        }

        val current = fragmentManager.findFragmentByTag(tag)
        fragmentManager.commit {
            if (current == null) {
                val fragment = fragmentManager.fragmentFactory.instantiate(
                    ClassLoader.getSystemClassLoader(),
                    className
                )
                add(containerId, fragment, tag)
            } else {
                show(current)
            }
            hideOthers(tag)
        }

        return destination
    }

    override fun popBackStack(): Boolean {
        val tag = backStack.pollLast() ?: return true
        val newCurrentTag = backStack.peekLast() ?: return true
        val newCurrent = fragmentManager.findFragmentByTag(newCurrentTag)
        fragmentManager.commit {
            newCurrent?.let {
                show(it)
                hideOthers(newCurrentTag)
            }
        }
        return true
    }

    private fun hideOthers(tag: String) {
        backStack.filter { it != tag }.forEach {
            val fragment = fragmentManager.findFragmentByTag(it)
            if (fragment != null) {
                fragmentManager.commit {
                    hide(fragment)
                }
            }
        }
    }
}