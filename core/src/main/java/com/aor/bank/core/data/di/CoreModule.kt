package com.aor.bank.core.data.di

import com.aor.bank.core.data.repository.UserRepository
import com.aor.bank.core.data.repository.UserRepositoryImpl
import com.aor.bank.core.data.repository.firebase.FirebaseAuthService
import com.aor.bank.core.data.repository.firebase.FirebaseAuthServiceImpl
import com.aor.bank.core.data.repository.firebase.FirebaseDatabaseBank
import com.aor.bank.core.data.repository.firebase.FirebaseDatabaseBankImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun bindFirebaseAuthService(
        firebaseAuthServiceImpl: FirebaseAuthServiceImpl
    ): FirebaseAuthService

    @Binds
    abstract fun bindFirebaseDatabaseBank(
        firebaseDatabaseBankImpl: FirebaseDatabaseBankImpl
    ): FirebaseDatabaseBank

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}