package com.aor.bank.core.data.model

data class TransactionModel(
    val id: String = "",
    val title: String = "",
    val subtitle: String = "",
    val amount: Double = 0.0,
    val date: String = ""
)