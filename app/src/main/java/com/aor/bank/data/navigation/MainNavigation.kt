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
fun MainNavigation(
    navController: NavHostController,
    navState: NavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = when (navState) {
            NavigationRoute.Onboarding -> NavigationRoute.Onboarding.route
            NavigationRoute.Home -> NavigationRoute.Home.route
            else -> NavigationRoute.Onboarding.route
        }
    ) {
        composable(NavigationRoute.Onboarding.route) {
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
                        popUpTo(NavigationRoute.Onboarding.route) { inclusive = true }
                    }
                },
                onBackButton = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavigationRoute.SignUp.route) {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(NavigationRoute.Home.route) {
                        popUpTo(NavigationRoute.Onboarding.route) { inclusive = true }
                    }
                },
                onBackButton = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavigationRoute.Home.route) {
            HomeScreen(
                onTransactionClick = { transaction ->
                    val route = NavigationRoute.TransactionDetail.createTransactionDetailsRoute(transaction)
                    navController.navigate(route)
                },
                onBackButton = {
                    navController.navigate(NavigationRoute.Onboarding.route) {
                        popUpTo(0)
                    }
                }
            )
        }

        composable(
            route = NavigationRoute.TransactionDetail.route,
            arguments = listOf(navArgument("transaction") { type = NavType.StringType })
        ) { backStackEntry ->
            val transactionJson = backStackEntry.arguments?.getString("transaction")
            val transaction = Gson().fromJson(transactionJson, TransactionModel::class.java)
            TransactionDetailsScreen(
                transaction = transaction,
                onBackButton = {
                    navController.popBackStack()
                }
            )
        }
    }
}