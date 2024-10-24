package com.aor.bank.core.data.repository.firebase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthService {

    override suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}