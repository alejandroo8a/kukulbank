package com.aor.bank.sign_in.data.repositories

interface SignInRepository {
    suspend fun signIn(email: String, password: String): Result<Unit>
}