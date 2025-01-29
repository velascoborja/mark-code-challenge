package com.medtronic.surgery.app.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medtronic.surgery.app.presentation.theme.AppTheme
import com.medtronic.surgery.app.presentation.theme.ColorAccent
import com.medtronic.surgery.app.presentation.theme.ColorPrimary

@Composable
fun MainNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        MainNavigationItem.ProceduresNavigationItem,
        MainNavigationItem.FavoritesNavigationItem,
    )
    NavigationBar {
        val currentRoute = getCurrentRoute(navController)
        val context = LocalContext.current
        navigationItems.onEach {
            NavigationBarItem(
                icon = {
                    Icon(painterResource(it.icon), contentDescription = it.route)
                },
                label = {
                    Text(context.resources.getString(it.label))
                },
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ColorPrimary,   // Change the selected icon color
                    unselectedIconColor = ColorAccent,  // Change the unselected icon color
                    selectedTextColor = ColorPrimary,   // Change the selected text color
                    unselectedTextColor = ColorAccent,  // Change the unselected text color
                    indicatorColor = ColorPrimary.copy(alpha = 0.1f)  // Change the indicator color for the selected item
                ),
            )
        }
    }
}

@Composable
private fun getCurrentRoute(navController: NavController): String? {
    val backStackEntry by navController.currentBackStackEntryAsState()
    return backStackEntry?.destination?.route
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    AppTheme {
        val navController = rememberNavController()
        MainNavigationBar(navController = navController)
    }
}