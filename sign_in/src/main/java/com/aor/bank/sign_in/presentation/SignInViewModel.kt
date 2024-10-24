package com.aor.bank.sign_in.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aor.bank.core.data.model.BaseState
import com.aor.bank.sign_in.domain.repositories.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BaseState>(BaseState.Init)
    val uiState: StateFlow<BaseState> = _uiState

    fun signIn(email: String, password: String) {
        _uiState.value = BaseState.Loading
        viewModelScope.launch {
            val result = signInRepository.signIn(email, password)
            _uiState.value = if (result.isSuccess) {
                BaseState.Success
            } else {
                BaseState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}