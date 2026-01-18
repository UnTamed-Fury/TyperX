package com.typerx.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.TypeConverters
import com.typerx.data.local.converters.DateConverter
import com.typerx.data.models.TypingResult

@Database(
    entities = [TypingResult::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class TypingDatabase : RoomDatabase() {
    abstract fun typingResultDao(): TypingResultDao
    
    companion object {
        @Volatile
        private var INSTANCE: TypingDatabase? = null
        
        fun getDatabase(context: Context): TypingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TypingDatabase::class.java,
                    "typing_database"
                )
                .fallbackToDestructiveMigration() // Recreates database if schema changes
                .build()
                
                INSTANCE = instance
                instance
            }
        }
    }
}