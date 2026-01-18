package com.typerx.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typerx.engine.TypingEngine
import com.typerx.data.repository.TypingResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TypingViewModel @Inject constructor(
    val repository: TypingResultRepository
) : ViewModel() {
    
    val typingEngine = TypingEngine(repository)
    
    fun saveResult() {
        viewModelScope.launch {
            // The engine already saves the result when session ends
        }
    }
}