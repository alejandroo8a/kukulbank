package com.aor.bank.core.data.repository

import com.aor.bank.core.data.model.UserModel

interface UserRepository {
    fun isUserLoggedIn(): Boolean
    fun signOut()
    suspend fun getUserDetails(): UserModel
}