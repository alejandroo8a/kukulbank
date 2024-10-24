package com.aor.bank.core.data.model

data class TransactionModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val amount: Double,
    val date: String
)