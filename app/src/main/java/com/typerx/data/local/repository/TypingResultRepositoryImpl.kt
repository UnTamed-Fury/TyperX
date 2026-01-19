package com.typerx.data.local.repository

import com.typerx.data.models.TypingResult
import com.typerx.data.repository.TypingResultRepository
import com.typerx.data.local.database.TypingResultDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TypingResultRepositoryImpl @Inject constructor(
    private val dao: TypingResultDao
) : TypingResultRepository {
    
    override fun getAllResults(): Flow<List<TypingResult>> {
        return dao.getAllResults()
    }
    
    override fun getResultById(id: String): Flow<TypingResult?> {
        return dao.getResultById(id)
    }
    
    override suspend fun insertResult(result: TypingResult) {
        dao.insertResult(result)
    }
    
    override suspend fun updateResult(result: TypingResult) {
        dao.updateResult(result)
    }
    
    override suspend fun deleteResult(result: TypingResult) {
        dao.deleteResult(result)
    }
    
    override suspend fun deleteAllResults() {
        dao.deleteAllResults()
    }
    
    override fun getTopResults(limit: Int): Flow<List<TypingResult>> {
        return dao.getTopResults(limit)
    }
    
    override fun getAverageWpm(): Flow<Double> {
        return dao.getAverageWpm().map { it ?: 0.0 }
    }
    
    override fun getAverageAccuracy(): Flow<Double> {
        return dao.getAverageAccuracy().map { it ?: 0.0 }
    }

    override fun getResultCount(): Flow<Int> {
        return dao.getResultCount()
    }
}