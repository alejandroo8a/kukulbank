package com.aor.bank.core.data.repository.firebase

import android.net.Uri

interface FirebaseAuthService {
    suspend fun createUserWithEmailAndPassword(name: String, lastName: String, email: String, password: String, photoUri: Uri): Result<Unit>
    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<Unit>
}
