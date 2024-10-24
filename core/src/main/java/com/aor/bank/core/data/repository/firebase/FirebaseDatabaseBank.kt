package com.aor.bank.core.data.repository.firebase

import com.aor.bank.core.data.model.TransactionModel

interface FirebaseDatabaseBank {
    suspend fun getTransactions(): List<TransactionModel>
    suspend fun getBalance(): Double
}