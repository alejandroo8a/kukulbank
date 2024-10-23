package com.aor.bank.sign_in.domain

import com.aor.bank.sign_in.data.repositories.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: SignInRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.signIn(email, password)
    }
}
