package com.typerx.data.repository

import com.typerx.data.models.TypingResult
import kotlinx.coroutines.flow.Flow

interface TypingResultRepository {
    fun getAllResults(): Flow<List<TypingResult>>
    fun getResultById(id: String): Flow<TypingResult?>
    suspend fun insertResult(result: TypingResult)
    suspend fun updateResult(result: TypingResult)
    suspend fun deleteResult(result: TypingResult)
    suspend fun deleteAllResults()
    fun getTopResults(limit: Int = 10): Flow<List<TypingResult>>
    fun getAverageWpm(): Flow<Double>
    fun getAverageAccuracy(): Flow<Double>
    fun getResultCount(): Flow<Int>
}