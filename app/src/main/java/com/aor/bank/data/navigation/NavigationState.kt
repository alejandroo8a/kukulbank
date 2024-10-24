package com.aor.bank.data.navigation

sealed class NavigationState {
    data object Onboarding : NavigationState()
    data object Home : NavigationState()
}