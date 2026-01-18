package com.typerx.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.typerx.ui.theme.ThemeType
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = viewModel()) {
    val fontSize by settingsViewModel.fontSize
    val themeTypeIndex by settingsViewModel.themeType
    val soundEnabled by settingsViewModel.soundEnabled
    val hapticEnabled by settingsViewModel.hapticEnabled
    
    var selectedTheme by remember { mutableStateOf(ThemeType.values()[themeTypeIndex]) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Theme selection
        Text(
            text = "Theme",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        ThemeType.values().forEachIndexed { index, theme ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedTheme == theme,
                    onClick = { 
                        selectedTheme = theme
                        settingsViewModel.setThemeType(index)
                    }
                )
                Text(text = theme.name.replace("_", " "))
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Font size slider
        Text(
            text = "Font Size: $fontSize sp",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Slider(
            value = fontSize.toFloat(),
            onValueChange = { settingsViewModel.setFontSize(it.toInt()) },
            valueRange = 12f..24f,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Sound toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = soundEnabled,
                onCheckedChange = { settingsViewModel.setSoundEnabled(it) }
            )
            Text(text = "Sound Effects")
        }
        
        // Haptic feedback toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = hapticEnabled,
                onCheckedChange = { settingsViewModel.setHapticEnabled(it) }
            )
            Text(text = "Haptic Feedback")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Daily goal setting
        Text(
            text = "Daily Goal",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "Set your daily typing practice goal in minutes",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // This would connect to DailyGoalManager in a real implementation
        OutlinedTextField(
            value = "15",
            onValueChange = { /* Handle daily goal change */ },
            label = { Text("Minutes") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}