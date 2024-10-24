package com.aor.bank.core.data.repository.firebase

interface FirebaseAuthService {
    suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<Unit>
}
