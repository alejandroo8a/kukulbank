package com.aor.bank.sign_up.data.di

import com.aor.bank.sign_up.data.repository.SignUpRepositoryImpl
import com.aor.bank.sign_up.domain.repository.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class SignUpModule {

    @Binds
    abstract fun bindSignUpRepository(
        signUpRepositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository
}