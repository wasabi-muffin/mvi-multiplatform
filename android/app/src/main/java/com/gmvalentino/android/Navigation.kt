package com.gmvalentino.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gmvalentino.android.task.AddTaskScreen
import com.gmvalentino.android.task.OverviewScreen

internal sealed class Screen(val route: String) {
    object Overview : Screen("overview")
    object AddTask : Screen("addTask")
}

@Composable
internal fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Overview.route
    ) {
        composable(
            route = Screen.Overview.route
        ) {
            OverviewScreen(navController)
        }

        composable(
            route = Screen.AddTask.route
        ) {
            AddTaskScreen()
        }
    }
}
