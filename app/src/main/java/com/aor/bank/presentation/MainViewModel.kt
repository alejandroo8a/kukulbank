package com.aor.bank.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aor.bank.core.data.repository.UserRepository
import com.aor.bank.data.navigation.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _navigationState = MutableStateFlow<NavigationState>(NavigationState.SignIn)
    val navigationState: StateFlow<NavigationState> = _navigationState

    fun checkUserStatus() {
        viewModelScope.launch {
            if (userRepository.isUserLoggedIn()) {
                _navigationState.value = NavigationState.Home
            } else {
                _navigationState.value = NavigationState.SignIn
            }
        }
    }

    fun signOut() {
        userRepository.signOut()
        _navigationState.value = NavigationState.SignIn
    }
}