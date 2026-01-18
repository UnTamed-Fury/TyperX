package com.typerx.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.typerx.engine.TypingSession
import com.typerx.ui.components.StatsCard
import androidx.lifecycle.viewmodel.compose.viewModel
import com.typerx.data.models.TypingResult
import java.util.Date
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    session: TypingSession?,
    resultViewModel: ResultViewModel = viewModel()
) {
    if (session == null) {
        // If no session is provided, show an empty state
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No typing session data available",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        return
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Results",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        )
        
        // Display final stats
        StatsCard(
            wpm = session.wpm,
            accuracy = session.accuracy,
            errors = session.errors
        )
        
        // Detailed stats
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                DetailStat("Duration", "${session.duration}s")
                DetailStat("Total Keystrokes", session.totalKeystrokes.toString())
                DetailStat("Correct Keystrokes", session.correctKeystrokes.toString())
                DetailStat("Language", if(session.language == "hi") "Hindi" else "English")
                DetailStat("Mode", session.mode)
                DetailStat("Date", Date(session.startTime).toString()) // Would be formatted in real app
            }
        }
        
        // Performance indicators
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Performance Analysis",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                // WPM progress indicator
                PerformanceIndicator(
                    label = "Speed",
                    value = session.wpm,
                    maxValue = 100.0, // Adjust based on practice mode
                    color = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Accuracy progress indicator
                PerformanceIndicator(
                    label = "Accuracy",
                    value = session.accuracy,
                    maxValue = 100.0,
                    color = MaterialTheme.colorScheme.secondary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Error rate indicator
                PerformanceIndicator(
                    label = "Errors",
                    value = session.errors.toDouble(),
                    maxValue = 20.0, // Adjust based on text length
                    color = MaterialTheme.colorScheme.error,
                    isReverse = true // Lower is better
                )
            }
        }
        
        // Action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { /* Navigate back to typing screen */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Try Again")
            }
            
            Button(
                onClick = {
                    val result = TypingResult(
                        text = session.text,
                        language = session.language,
                        mode = session.mode,
                        duration = session.duration,
                        wpm = session.wpm,
                        accuracy = session.accuracy,
                        errors = session.errors,
                        totalKeystrokes = session.totalKeystrokes,
                        correctKeystrokes = session.correctKeystrokes,
                        elapsedTime = session.endTime - session.startTime
                    )
                    resultViewModel.saveResult(result)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Save Result")
            }
        }
    }
}

@Composable
fun DetailStat(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun PerformanceIndicator(
    label: String,
    value: Double,
    maxValue: Double,
    color: androidx.compose.ui.graphics.Color,
    isReverse: Boolean = false
) {
    val progress = if (maxValue > 0) {
        if (isReverse) {
            1f - (value.toFloat() / maxValue.toFloat()).coerceIn(0f, 1f)
        } else {
            (value.toFloat() / maxValue.toFloat()).coerceIn(0f, 1f)
        }
    } else 0f
    
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = if (isReverse) "${(maxValue - value).toInt()}" else value.toInt().toString(),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = color
        )
    }
}