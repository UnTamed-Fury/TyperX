package com.typerx.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.typerx.keyboard.KrutidevLayout

@Composable
fun TextInputView(
    referenceText: String,
    typedText: String,
    isHindiMode: Boolean = false,
    onInput: (String) -> Unit,
    onBackspace: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    
    // Custom text selection colors to make selections visible in all themes
    val customTextSelectionColors = TextSelectionColors(
        backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        handleColor = MaterialTheme.colorScheme.primary
    )
    
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Reference text (what the user should type)
            Text(
                text = if (isHindiMode) KrutidevLayout.convertToHindi(referenceText) else referenceText,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            
            // Typed text with error highlighting
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                // Background for typed text area
                Text(
                    text = if (isHindiMode) KrutidevLayout.convertToHindi(typedText) else typedText,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.align(Alignment.TopStart)
                )
                
                // Placeholder for where user will type next
                if (typedText.isEmpty()) {
                    Text(
                        text = "Start typing here...",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                }
            }
            
            // Hidden text field to capture input
            BasicTextField(
                value = typedText,
                onValueChange = { newText ->
                    // Calculate what was added
                    if (newText.length > typedText.length) {
                        val addedText = newText.substring(typedText.length)
                        onInput(addedText)
                    } else if (newText.length < typedText.length) {
                        // Handle backspace - this is simplified
                        onBackspace()
                    }
                },
                textStyle = TextStyle(color = Color.Transparent), // Make text invisible
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp) // Very thin to minimize visibility
                    .focusRequester(focusRequester)
            )
        }
    }
}