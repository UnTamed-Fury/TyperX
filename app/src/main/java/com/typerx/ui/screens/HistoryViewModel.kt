package com.typerx.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typerx.data.models.TypingResult
import com.typerx.data.repository.TypingResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: TypingResultRepository
) : ViewModel() {
    
    val allResults = repository.getAllResults()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    val averageWpm = repository.getAverageWpm()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )
    
    val averageAccuracy = repository.getAverageAccuracy()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )
    
    val resultCount = repository.getResultCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )
    
    fun deleteResult(result: TypingResult) {
        viewModelScope.launch {
            repository.deleteResult(result)
        }
    }
    
    fun deleteAllResults() {
        viewModelScope.launch {
            repository.deleteAllResults()
        }
    }
}