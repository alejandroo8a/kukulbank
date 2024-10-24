package com.aor.bank.home.data.repository

import com.aor.bank.core.data.model.TransactionModel
import com.aor.bank.core.data.repository.firebase.FirebaseDatabaseBank
import com.aor.bank.home.domain.repository.UserTransactionsRepository
import javax.inject.Inject

class UserTransactionsRepositoryImpl @Inject constructor(
    private val firebaseDatabaseBank: FirebaseDatabaseBank
) : UserTransactionsRepository {

    override suspend fun getTransactions(): List<TransactionModel> {
        return firebaseDatabaseBank.getTransactions()
    }

    override suspend fun getBalance(): Double {
        return firebaseDatabaseBank.getBalance()
    }
}
