package com.aor.bank.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aor.bank.core.data.navigation.NavigationRoute
import com.aor.bank.core.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _navigationState = MutableStateFlow<NavigationRoute>(NavigationRoute.Loading)
    val navigationState: StateFlow<NavigationRoute> = _navigationState

    fun checkUserStatus() {
        viewModelScope.launch {
            if (userRepository.isUserLoggedIn()) {
                _navigationState.value = NavigationRoute.Home
            } else {
                _navigationState.value = NavigationRoute.Onboarding
            }
        }
    }
}