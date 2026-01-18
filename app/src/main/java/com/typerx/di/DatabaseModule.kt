package com.typerx.di

import android.content.Context
import com.typerx.data.local.database.TypingDatabase
import com.typerx.data.local.database.TypingResultDao
import com.typerx.data.local.repository.TypingResultRepositoryImpl
import com.typerx.data.repository.TypingResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideTypingDatabase(@ApplicationContext context: Context): TypingDatabase {
        return TypingDatabase.getDatabase(context)
    }
    
    @Provides
    fun provideTypingResultDao(database: TypingDatabase): TypingResultDao {
        return database.typingResultDao()
    }
    
    @Provides
    fun provideTypingResultRepository(dao: TypingResultDao): TypingResultRepository {
        return TypingResultRepositoryImpl(dao)
    }
}