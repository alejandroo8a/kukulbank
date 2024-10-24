package com.aor.bank.sign_up.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aor.bank.core.data.model.BaseState
import com.aor.bank.sign_up.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BaseState>(BaseState.Init)
    val uiState: StateFlow<BaseState> = _uiState

    fun signUp(name: String, lastName: String, email: String, password: String, photoUri: Uri?) {
        viewModelScope.launch {
            _uiState.value = BaseState.Loading
            val result = signUpRepository.createUser(name, lastName, email, password, photoUri)
            if (result.isSuccess) {
                _uiState.value = BaseState.Success
            } else {
                _uiState.value = BaseState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }
}
