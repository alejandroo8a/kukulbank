package com.aor.bank.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aor.bank.core.data.model.TransactionModel
import com.aor.bank.core.data.model.UserModel
import com.aor.bank.core.data.repository.UserRepository
import com.aor.bank.home.domain.repository.UserTransactionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userTransactionsRepository: UserTransactionsRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserModel?>(null)
    val user: StateFlow<UserModel?> = _user

    private val _transactions = MutableStateFlow<List<TransactionModel>>(emptyList())
    val transactions: StateFlow<List<TransactionModel>> = _transactions

    private val _balance = MutableStateFlow(0.0)
    val balance: StateFlow<Double> = _balance

    private val _logoutState = MutableStateFlow(false)
    val logoutState: StateFlow<Boolean> = _logoutState

    init {
        fetchUserDetails()
        //fetchTransactions()
        fetchBalance()
    }

    private fun fetchUserDetails() {
        viewModelScope.launch {
            try {
                val userDetails = userRepository.getUserDetails()
                _user.value = userDetails
            } catch (e: Exception) {
                // Handle error, e.g., log it or show error message
                Log.e("HomeViewModel", "Error fetching user details: ${e.message}")
            }
        }
    }
/*
    private fun fetchTransactions() {
        viewModelScope.launch {
            try {
                val userTransactions = userRepository.getTransactions()
                _transactions.value = userTransactions
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching transactions: ${e.message}")
            }
        }
    }*/

    private fun fetchBalance() {
        viewModelScope.launch {
            try {
                val userBalance = userTransactionsRepository.getBalance()
                _balance.value = userBalance
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching balance: ${e.message}")
            }
        }
    }

    fun logout() {
        userRepository.signOut()
        _logoutState.value = true // Trigger logout state change
    }
}
