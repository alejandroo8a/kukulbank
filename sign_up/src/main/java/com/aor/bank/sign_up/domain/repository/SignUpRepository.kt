package com.aor.bank.sign_up.domain.repository

import android.net.Uri

interface SignUpRepository {
    suspend fun createUser(
        name: String,
        lastName: String,
        email: String,
        password: String,
        photoUri: Uri?
    ): Result<Unit>
}