package com.pseedk.infabfilms.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pseedk.infabfilms.MainViewModel
import com.pseedk.infabfilms.screens.DetailsScreen
import com.pseedk.infabfilms.screens.MainScreen
import com.pseedk.infabfilms.screens.SplashScreen
import com.pseedk.infabfilms.utils.Constants.Screens.DETAILS_SCREEN
import com.pseedk.infabfilms.utils.Constants.Screens.MAIN_SCREEN
import com.pseedk.infabfilms.utils.Constants.Screens.SPLASH_SCREEN

sealed class Screens(val route: String) {
    object Splash : Screens(route = SPLASH_SCREEN)
    object Main : Screens(route = MAIN_SCREEN)
    object Details : Screens(route = DETAILS_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Details.route + "/{Id}") { backStackEntry ->
            DetailsScreen(
                viewModel = viewModel,
                itemId = backStackEntry.arguments?.getString("Id") ?: "1"
            )
        }
    }
}