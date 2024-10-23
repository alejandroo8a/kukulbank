package com.aor.bank.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aor.bank.core.data.navigation.NavigationRoute
import com.aor.bank.sign_in.presentation.SignInScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.SignIn.route
    ) {
        // SignIn Screen
        composable(NavigationRoute.SignIn.route) {
            SignInScreen(onSignInSuccess = {
                navController.navigate(NavigationRoute.Home.route) {
                    popUpTo(NavigationRoute.SignIn.route) { inclusive = true }
                }
            })
        }

        // Home Screen
        composable(NavigationRoute.Home.route) {
            //HomeScreen()
        }
    }
}