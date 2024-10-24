package com.aor.bank.sign_up.data.repository

import android.net.Uri
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
        password: String,
        photoUri: Uri?
    ): Result<Unit> {
        val errorMessage = listOfNotNull(
            ValidationUtil.validateName(name),
            ValidationUtil.validateLastName(lastName),
            ValidationUtil.validateEmail(email),
            ValidationUtil.validatePassword(password),
            ValidationUtil.validatePhoto(photoUri),
        ).joinToString("\n")

        if (errorMessage.isNotEmpty()) return Result.failure(Exception(errorMessage))

        return firebaseAuth.createUserWithEmailAndPassword(name, lastName, email, password, photoUri!!)
    }
}