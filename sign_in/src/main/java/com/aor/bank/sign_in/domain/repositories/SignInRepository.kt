package com.aor.bank.sign_in.domain.repositories

interface SignInRepository {
    suspend fun signIn(email: String, password: String): Result<Unit>
}