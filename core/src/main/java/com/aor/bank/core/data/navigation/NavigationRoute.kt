package com.aor.bank.core.data.navigation

sealed class NavigationRoute(val route: String) {
    data object OnboardingScreen : NavigationRoute("onboarding")
    data object Home : NavigationRoute("home")
    data object SignIn : NavigationRoute("sign_in")
    data object SignUp : NavigationRoute("sign_up")
    data object TransactionDetail : NavigationRoute("transaction_detail/{transactionId}") {
        fun createRoute(transactionId: String) = "transaction_detail/$transactionId"
    }
}