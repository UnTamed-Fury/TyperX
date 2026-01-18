package com.typerx.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.typerx.engine.WpmCalculator
import com.typerx.engine.AccuracyTracker
import com.typerx.ui.components.TextInputView
import com.typerx.ui.components.TimerView
import com.typerx.ui.components.StatsCard
import androidx.lifecycle.viewmodel.compose.viewModel
import com.typerx.utils.SampleTextProvider
import com.typerx.data.models.PracticeCategory
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.shadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypingScreen(typingViewModel: TypingViewModel = viewModel()) {
    val typingEngine = typingViewModel.typingEngine
    val session by typingEngine.sessionState
    val timer by typingEngine.timerState
    val progress by typingEngine.progressState
    
    // Sample text for typing practice
    val sampleText = SampleTextProvider.getSampleText(PracticeCategory.LOWERCASE_UPPERCASE, "en")
    
    LaunchedEffect(Unit) {
        typingEngine.startNewSession(sampleText, duration = 60)
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header with stats
            session?.let { s ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatChip(
                            label = "WPM",
                            value = s.wpm.toInt().toString(),
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        VerticalDivider()
                        
                        StatChip(
                            label = "ACC",
                            value = "${s.accuracy.toInt()}%",
                            color = MaterialTheme.colorScheme.secondary
                        )
                        
                        VerticalDivider()
                        
                        StatChip(
                            label = "ERR",
                            value = s.errors.toString(),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            
            // Timer with circular progress
            session?.let { s ->
                CircularTimerView(
                    timeLeft = timer,
                    totalTime = s.duration.toLong(),
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )
            }
            
            // Text input area
            session?.let { s ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    TextInputView(
                        referenceText = s.text,
                        typedText = s.typedText,
                        isHindiMode = s.language == "hi",
                        onInput = { input ->
                            typingEngine.processInput(input)
                        },
                        onBackspace = {
                            typingEngine.backspace()
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    )
                }
            }
            
            // Action buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { typingEngine.resetSession() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Reset")
                }
                
                var isHindiMode by remember { mutableStateOf(false) }
                Button(
                    onClick = { 
                        isHindiMode = !isHindiMode
                        typingEngine.resetSession()
                        typingEngine.startNewSession(
                            SampleTextProvider.getSampleText(
                                PracticeCategory.LOWERCASE_UPPERCASE,
                                if(isHindiMode) "hi" else "en"
                            ),
                            language = if(isHindiMode) "hi" else "en",
                            duration = 60
                        )
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if(isHindiMode) "हिं/En" else "En/हिं")
                }
            }
        }
        
        // Floating action button for additional options
        FloatingActionButton(
            onClick = { /* Open options menu */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .shadow(8.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.MoreVert,
                contentDescription = "Options"
            )
        }
    }
}

@Composable
fun StatChip(label: String, value: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = color
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun VerticalDivider() {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp),
        color = MaterialTheme.colorScheme.outline
    )
}

@Composable
fun CircularTimerView(timeLeft: Long, totalTime: Long, modifier: Modifier = Modifier) {
    val progress = if (totalTime > 0) {
        (timeLeft.toFloat() / totalTime).coerceIn(0f, 1f)
    } else 0f
    
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.size(120.dp)) {
            val strokeWidth = 8.dp.toPx()
            val radius = (size.minDimension - strokeWidth) / 2
            val center = Offset(size.width / 2, size.height / 2)
            
            // Background circle
            drawCircle(
                color = MaterialTheme.colorScheme.surfaceVariant,
                radius = radius,
                center = center,
                style = androidx.compose.ui.graphics.drawscope.Stroke(strokeWidth)
            )
            
            // Progress arc
            val sweepAngle = 360f * progress
            drawArc(
                color = MaterialTheme.colorScheme.primary,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                center = center,
                radius = radius,
                style = androidx.compose.ui.graphics.drawscope.Stroke(strokeWidth, cap = androidx.compose.ui.graphics.StrokeCap.Round)
            )
        }
        
        Text(
            text = "${timeLeft}s",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}