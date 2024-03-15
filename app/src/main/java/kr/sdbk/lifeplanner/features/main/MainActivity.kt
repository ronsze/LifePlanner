package kr.sdbk.lifeplanner.features.main

import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.lifeplanner.R
import kr.sdbk.lifeplanner.base.BaseActivity
import kr.sdbk.lifeplanner.databinding.ActivityMainBinding
import kr.sdbk.lifeplanner.features.diary.DiaryFragment
import kr.sdbk.lifeplanner.features.schedule.ScheduleFragment
import kr.sdbk.lifeplanner.features.setting.SettingFragment

@AndroidEntryPoint
class MainActivity: BaseActivity<MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var currentMenu: MainMenu = MainMenu.Schedule

    @Composable
    override fun InitView() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                factory = {
                    binding.root
                }
            )
            BottomNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }

    @Composable
    private fun BottomNavigationBar(
        modifier: Modifier
    ) {
        BottomNavigation(modifier = modifier) {
            val selectedMenu: MutableState<MainMenu> = remember { mutableStateOf(currentMenu) }
            if (supportFragmentManager.fragments.isEmpty()) initFragment(currentMenu)
            NavItem(scope = this, icon = R.drawable.ic_launcher_background, menu = MainMenu.Schedule, selectedMenu)
            NavItem(scope = this, icon = R.drawable.ic_launcher_foreground, menu = MainMenu.Diary, selectedMenu)
            NavItem(scope = this, icon = R.drawable.ic_launcher_foreground, menu = MainMenu.Setting, selectedMenu)
        }
    }

    @Composable
    private fun NavItem(
        scope: RowScope,
        @DrawableRes icon: Int,
        menu: MainMenu,
        selectedMenu: MutableState<MainMenu>
    ) = scope.run {
        BottomNavigationItem(
            icon = { NavIcon(resourceId = icon) },
            label = { Text(text = stringResource(id = menu.label), fontSize = 16.sp) },
            selectedContentColor = Color.Cyan,
            unselectedContentColor = Color.LightGray,
            alwaysShowLabel = true,
            selected = menu == selectedMenu.value,
            onClick = {
                selectedMenu.value = menu
                onClickNavItem(menu)
            }
        )
    }

    @Composable
    private fun NavIcon(resourceId: Int) {
        Icon(
            painter = painterResource(id = resourceId),
            contentDescription = "",
            modifier = Modifier.size(75.dp)
        )
    }

    private fun onClickNavItem(menu: MainMenu) {
        val tag = getString(menu.label)
        val existingFragment = supportFragmentManager.findFragmentByTag(tag)
        existingFragment?.run {
            replaceFragment(this, tag)
        } ?: initFragment(menu)
        currentMenu = menu
    }

    private fun initFragment(menu: MainMenu) {
        val tag = getString(menu.label)
        val fragment = when (menu) {
            MainMenu.Schedule -> ScheduleFragment()
            MainMenu.Diary -> DiaryFragment()
            MainMenu.Setting -> SettingFragment()
        }
        supportFragmentManager.beginTransaction().add(R.id.main_container, fragment, tag).commit()
        hideFragments(tag)
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().show(fragment).commit()
        hideFragments(tag)
    }

    private fun hideFragments(tag: String) {
        supportFragmentManager.fragments.forEach {
            if (it.tag != tag && !it.isHidden)
                supportFragmentManager.beginTransaction().hide(it).commit()
        }
    }

    sealed class MainMenu(@StringRes val label: Int) {
        data object Schedule: MainMenu(R.string.title_schedule)
        data object Diary: MainMenu(R.string.title_diary)
        data object Setting: MainMenu(R.string.title_setting)
    }

    @Preview()
    @Composable
    fun NavItemPreview() {
        Row {
            NavItem(scope = this, icon = R.drawable.ic_launcher_foreground, menu = MainMenu.Diary, mutableStateOf(MainMenu.Diary))
        }
    }
}