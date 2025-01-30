package com.medtronic.surgery.app.di

import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepository
import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProcedureRepository(
        authRepositoryImpl: ProcedureRepositoryImpl
    ): ProcedureRepository
}