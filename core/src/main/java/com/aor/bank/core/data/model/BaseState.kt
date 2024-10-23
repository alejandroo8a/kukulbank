package com.aor.bank.core.data.model

sealed class BaseState {
    data object Init : BaseState()
    data object Loading : BaseState()
    data object Success : BaseState()
    data class Error(val message: String) : BaseState()
}
