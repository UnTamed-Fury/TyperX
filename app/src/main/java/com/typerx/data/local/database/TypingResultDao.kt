package com.typerx.data.local.database

import androidx.room.*
import com.typerx.data.models.TypingResult
import kotlinx.coroutines.flow.Flow

@Dao
interface TypingResultDao {
    @Query("SELECT * FROM typing_sessions ORDER BY date DESC")
    fun getAllResults(): Flow<List<TypingResult>>
    
    @Query("SELECT * FROM typing_sessions WHERE id = :id")
    fun getResultById(id: String): Flow<TypingResult>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: TypingResult)
    
    @Update
    suspend fun updateResult(result: TypingResult)
    
    @Delete
    suspend fun deleteResult(result: TypingResult)
    
    @Query("DELETE FROM typing_sessions")
    suspend fun deleteAllResults()
    
    @Query("SELECT * FROM typing_sessions ORDER BY wpm DESC LIMIT :limit")
    fun getTopResults(limit: Int): Flow<List<TypingResult>>
    
    @Query("SELECT AVG(wpm) FROM typing_sessions")
    fun getAverageWpm(): Flow<Double?>
    
    @Query("SELECT AVG(accuracy) FROM typing_sessions")
    fun getAverageAccuracy(): Flow<Double?>
    
    @Query("SELECT COUNT(*) FROM typing_sessions")
    fun getResultCount(): Flow<Int>
}