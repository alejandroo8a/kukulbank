package com.aor.bank.sign_up.data.repository

import com.aor.bank.core.data.repository.firebase.FirebaseAuthService
import com.aor.bank.sign_up.data.utils.ValidationUtil
import com.aor.bank.sign_up.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuthService
) : SignUpRepository {

    override suspend fun createUser(
        name: String,
        lastName: String,
        email: String,
        password: String
    ): Result<Unit> {
        val errorMessage = listOfNotNull(
            ValidationUtil.validateName(name),
            ValidationUtil.validateLastName(lastName),
            ValidationUtil.validateEmail(email),
            ValidationUtil.validatePassword(password)
        ).joinToString("\n")

        if (errorMessage.isNotEmpty()) return Result.failure(Exception(errorMessage))

        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Error al crear la cuenta: ${e.message}"))
        }
    }
}