package com.aor.bank.sign_in.data.repositories

import com.aor.bank.sign_in.domain.repositories.SignInRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SignInRepository {
    override suspend fun signIn(email: String, password: String): Result<Unit> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}