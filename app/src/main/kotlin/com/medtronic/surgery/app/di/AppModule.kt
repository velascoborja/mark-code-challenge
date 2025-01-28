package com.medtronic.surgery.app.di

import android.content.Context
import androidx.room.Room
import com.medtronic.surgery.app.data.local.MRoomDatabase
import com.medtronic.surgery.app.data.local.dao.ProcedureDao
import com.medtronic.surgery.app.data.local.dao.ProcedureDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext appContext: Context
    ): MRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            MRoomDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesProcedureDao(db: MRoomDatabase): ProcedureDao {
        return db.procedureDao()
    }

    @Provides
    fun providesProcedureDetailsDao(db: MRoomDatabase): ProcedureDetailsDao {
        return db.procedureDetailsDao()
    }

    private const val DATABASE_NAME = "medtronic_surgery_app_db"
}