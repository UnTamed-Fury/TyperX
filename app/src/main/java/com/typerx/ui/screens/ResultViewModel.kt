package com.typerx.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typerx.data.models.TypingResult
import com.typerx.data.repository.TypingResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: TypingResultRepository
) : ViewModel() {
    
    fun saveResult(result: TypingResult) {
        viewModelScope.launch {
            repository.insertResult(result)
        }
    }
}