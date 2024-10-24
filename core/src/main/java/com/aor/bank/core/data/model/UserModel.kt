package com.aor.bank.core.data.model

data class UserModel(
    val id: String,
    val name: String,
    val lastName: String,
    val email: String,
    val photoUrl: String? = null
)