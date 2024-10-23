package com.aor.bank.data.navigation

sealed class NavigationState {
    data object SignIn : NavigationState()
    data object Home : NavigationState()
}