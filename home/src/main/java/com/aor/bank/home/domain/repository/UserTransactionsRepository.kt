package com.aor.bank.home.domain.repository

import com.aor.bank.core.data.model.TransactionModel

interface UserTransactionsRepository {
    suspend fun getTransactions(): List<TransactionModel>
    suspend fun getBalance(): Double
}