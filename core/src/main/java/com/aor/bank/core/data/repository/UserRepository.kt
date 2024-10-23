package com.aor.bank.core.data.repository

interface UserRepository {
    fun isUserLoggedIn(): Boolean
    fun signOut()
}