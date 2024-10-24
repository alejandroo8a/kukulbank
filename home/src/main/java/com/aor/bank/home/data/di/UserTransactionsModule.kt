package com.aor.bank.home.data.di

import com.aor.bank.home.data.repository.UserTransactionsRepositoryImpl
import com.aor.bank.home.domain.repository.UserTransactionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserTransactionsModule {

    @Binds
    abstract fun bindUserTransactionsRepository(
        impl: UserTransactionsRepositoryImpl
    ): UserTransactionsRepository
}