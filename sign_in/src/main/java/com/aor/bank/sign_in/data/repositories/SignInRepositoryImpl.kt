package com.aor.bank.sign_in.data.repositories

import com.aor.bank.core.data.repository.firebase.FirebaseAuthService
import com.aor.bank.core.data.util.ValidationUtil
import com.aor.bank.sign_in.domain.repositories.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService
) : SignInRepository {
    override suspend fun signIn(email: String, password: String): Result<Unit> {
        val errorMessage = listOfNotNull(
            ValidationUtil.validateEmail(email),
            ValidationUtil.validatePassword(password),
        ).joinToString("\n")

        if (errorMessage.isNotEmpty()) return Result.failure(Exception(errorMessage))
        return firebaseAuthService.signInWithEmailAndPassword(email, password)
    }
}