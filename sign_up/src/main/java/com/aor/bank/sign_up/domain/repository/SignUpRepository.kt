package com.aor.bank.sign_up.domain.repository

interface SignUpRepository {
    suspend fun createUser(
        name: String,
        lastName: String,
        email: String,
        password: String
    ): Result<Unit>
}