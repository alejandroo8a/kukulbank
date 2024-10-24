package com.aor.bank.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aor.bank.core.data.model.TransactionModel
import com.aor.bank.core.data.navigation.NavigationRoute
import com.aor.bank.core.presentation.OnboardingScreen
import com.aor.bank.home.presentation.HomeScreen
import com.aor.bank.sign_in.presentation.SignInScreen
import com.aor.bank.sign_up.presentation.SignUpScreen
import com.aor.bank.transaction.presentation.TransactionDetailsScreen
import com.google.gson.Gson

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.OnboardingScreen.route
    ) {
        composable(NavigationRoute.OnboardingScreen.route) {
            OnboardingScreen(
                onSignInClicked = {
                    navController.navigate(NavigationRoute.SignIn.route)
                },
                onCreateAccountClicked = {
                    navController.navigate(NavigationRoute.SignUp.route)
                }
            )
        }

        composable(NavigationRoute.SignIn.route) {
            SignInScreen(
                onSignInSuccess = {
                    navController.navigate(NavigationRoute.Home.route) {
                        popUpTo(NavigationRoute.OnboardingScreen.route) { inclusive = true }
                    }
                },
                navController = navController
            )
        }

        composable(NavigationRoute.SignUp.route) {
            SignUpScreen(
                navController = navController
            )
        }

        composable(NavigationRoute.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = NavigationRoute.TransactionDetail.route,
            arguments = listOf(navArgument("transaction") { type = NavType.StringType })
        ) { backStackEntry ->
            val transactionJson = backStackEntry.arguments?.getString("transaction")
            val transaction = Gson().fromJson(transactionJson, TransactionModel::class.java)

            TransactionDetailsScreen(
                transaction = transaction,
                navController = navController
            )
        }
    }
}