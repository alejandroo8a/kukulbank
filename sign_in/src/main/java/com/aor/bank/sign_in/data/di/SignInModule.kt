package com.aor.bank.sign_in.data.di

import com.aor.bank.sign_in.domain.repositories.SignInRepository
import com.aor.bank.sign_in.data.repositories.SignInRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SignInModule {
    @Binds
    abstract fun bindSignInRepository(
        impl: SignInRepositoryImpl
    ): SignInRepository
}