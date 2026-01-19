package fury.typerx.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fury.typerx.engine.TypingEngine
import fury.typerx.data.models.TypingSession

@Composable
fun TypingScreen(typingViewModel: TypingViewModel = TypingViewModel()) {
    val typingEngine = typingViewModel.typingEngine
    val session by typingEngine.sessionState.collectAsState()
    val timer by typingEngine.timerState.collectAsState()

    LaunchedEffect(Unit) {
        typingEngine.startNewSession("Welcome to TyperX! Start typing here...")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Stats header
            if (session != null) {
                val s = session!!
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatChip(label = "WPM", value = s.wpm.toInt().toString())
                        Divider()
                        StatChip(label = "ACC", value = "${s.accuracy.toInt()}%")
                        Divider()
                        StatChip(label = "ERR", value = s.errors.toString())
                    }
                }
            }

            // Timer
            if (session != null) {
                Text(
                    text = "${timer}s",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )
            }

            // Text display area
            if (session != null) {
                val s = session!!
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = s.text,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
            }

            // Input area
            TextField(
                value = session?.typedText ?: "",
                onValueChange = { newValue ->
                    // Calculate what was added
                    if (newValue.length > (session?.typedText?.length ?: 0)) {
                        val addedText = newValue.substring((session?.typedText?.length ?: 0))
                        typingEngine.processInput(addedText)
                    }
                },
                label = { Text("Type here") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            // Action buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { typingEngine.resetSession() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Reset")
                }

                Button(
                    onClick = { typingEngine.endSession() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Finish")
                }
            }
        }
    }
}

@Composable
fun StatChip(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}