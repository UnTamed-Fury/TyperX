package com.typerx.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typerx.ui.theme.ThemeType
import com.typerx.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    
    val fontSize = preferencesManager.fontSize
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 16
        )
    
    val themeType = preferencesManager.themeType
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0 // Default to DYNAMIC theme
        )
    
    val soundEnabled = preferencesManager.soundEnabled
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )
    
    val hapticEnabled = preferencesManager.hapticEnabled
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )
    
    fun setFontSize(size: Int) {
        viewModelScope.launch {
            preferencesManager.setFontSize(size)
        }
    }
    
    fun setThemeType(themeIndex: Int) {
        viewModelScope.launch {
            preferencesManager.setThemeType(themeIndex)
        }
    }
    
    fun setSoundEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setSoundEnabled(enabled)
        }
    }
    
    fun setHapticEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setHapticEnabled(enabled)
        }
    }
}