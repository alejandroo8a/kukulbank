package com.aor.bank.core.data.repository.firebase

import com.aor.bank.core.data.model.TransactionModel
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.random.Random

class FirebaseDatabaseBankImpl @Inject constructor(
    private val databaseReference: DatabaseReference
) : FirebaseDatabaseBank {

    override suspend fun getTransactions(): List<TransactionModel> {
        val randomId = getRandomId()

        val snapshot = databaseReference
            .child("transactions")
            .child(randomId.toString())
            .get()
            .await()

        val transactions = mutableListOf<TransactionModel>()
        snapshot.children.forEach { transactionSnapshot ->
            val transaction = transactionSnapshot.getValue(TransactionModel::class.java)
            transaction?.let { transactions.add(it) }
        }
        return transactions
    }

    override suspend fun getBalance(): Double {
        val randomId = getRandomId()

        val balanceSnapshot = databaseReference
            .child("balance")
            .child(randomId.toString())
            .child("amount")
            .get()
            .await()

        return balanceSnapshot.getValue(Double::class.java) ?: 0.0
    }

    private fun getRandomId(): Int {
        return Random.nextInt(1, 8)
    }
}