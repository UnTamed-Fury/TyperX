package fury.typerx.ui.screens

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fury.typerx.engine.TypingEngine
import fury.typerx.keyboard.RemingtonKeyMapper

@Composable
fun TypingScreen(onBack: () -> Unit) {
    val engine = remember { TypingEngine() }
    val state by engine.state.collectAsState()
    val focusRequester = remember { FocusRequester() }
    
    val keyHandler = remember {
        fury.typerx.keyboard.PhysicalKeyHandler(
            onChar = { engine.handleKeyPress(it) },
            onBackspace = { engine.handleBackspace() }
        )
    }
    
    // Placeholder for Krutidev font. 
    // In a real device with the font file in res/font/krutidev_gail.ttf, use:
    // val krutidevFontFamily = FontFamily(Font(R.font.krutidev_gail))
    val krutidevFontFamily = FontFamily.Default

    LaunchedEffect(Unit) {
        // Example text: "The quick brown fox" -> converted to Krutidev mapping or raw string
        // For CPCT (Hindi), the target text should be provided in Legacy encoding (ASCII chars that look like Hindi)
        engine.startSession("The quick brown fox jumps over the lazy dog", 60)
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .focusable()
            .onKeyEvent { event ->
                keyHandler.handle(event)
            }
    ) {
        Text("WPM: ${state.wpm} | Accuracy: ${state.accuracy}% | Time: ${state.timeRemaining}s")
        
        Text(
            text = "Target Text (Visual/Legacy):",
            fontSize = 12.sp
        )
        Text(
            text = state.targetText,
            fontFamily = krutidevFontFamily,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        Text(
            text = "Typed Text:",
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = state.typedText,
            fontFamily = krutidevFontFamily,
            fontSize = 24.sp,
            color = androidx.compose.ui.graphics.Color.Blue,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        if (state.isFinished) {
             Text(
                text = "Session Finished!",
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
