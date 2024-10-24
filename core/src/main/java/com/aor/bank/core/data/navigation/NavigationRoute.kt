package com.aor.bank.core.data.navigation

import com.aor.bank.core.data.model.TransactionModel
import com.google.gson.Gson

sealed class NavigationRoute(val route: String) {
    data object OnboardingScreen : NavigationRoute("onboarding")
    data object Home : NavigationRoute("home")
    data object SignIn : NavigationRoute("sign_in")
    data object SignUp : NavigationRoute("sign_up")
    data object TransactionDetail : NavigationRoute("transaction_detail/{transaction}")

    fun createTransactionDetailsRoute(transaction: TransactionModel): String {
        val transactionJson = Gson().toJson(transaction)
        return "transaction_detail/$transactionJson"
    }
}