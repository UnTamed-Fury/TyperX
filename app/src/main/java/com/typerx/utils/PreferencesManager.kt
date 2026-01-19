package com.typerx.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesManager(private val context: Context) {
    
    companion object {
        private val FONT_SIZE_KEY = intPreferencesKey("font_size")
        private val THEME_TYPE_KEY = intPreferencesKey("theme_type")
        private val SOUND_ENABLED_KEY = booleanPreferencesKey("sound_enabled")
        private val HAPTIC_ENABLED_KEY = booleanPreferencesKey("haptic_enabled")
    }
    
    val fontSize = context.dataStore.data.map { preferences ->
        preferences[FONT_SIZE_KEY] ?: 16
    }
    
    val themeType = context.dataStore.data.map { preferences ->
        preferences[THEME_TYPE_KEY] ?: 0 // Default to DYNAMIC theme
    }
    
    val soundEnabled = context.dataStore.data.map { preferences ->
        preferences[SOUND_ENABLED_KEY] ?: true
    }
    
    val hapticEnabled = context.dataStore.data.map { preferences ->
        preferences[HAPTIC_ENABLED_KEY] ?: true
    }
    
    suspend fun setFontSize(size: Int) {
        context.dataStore.edit { preferences ->
            preferences[FONT_SIZE_KEY] = size
        }
    }
    
    suspend fun setThemeType(themeIndex: Int) {
        context.dataStore.edit { preferences ->
            preferences[THEME_TYPE_KEY] = themeIndex
        }
    }
    
    suspend fun setSoundEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SOUND_ENABLED_KEY] = enabled
        }
    }
    
    suspend fun setHapticEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[HAPTIC_ENABLED_KEY] = enabled
        }
    }
}