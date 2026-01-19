package com.typerx.keyboard

import android.view.KeyEvent
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.input.key.*

@Composable
fun PhysicalKeyboard(
    onInput: (String) -> Unit,
    onBackspace: () -> Unit,
    onEnter: () -> Unit,
    onDelete: () -> Unit,
    onTab: () -> Unit,
    onSpace: () -> Unit,
    onEscape: () -> Unit
) {
    val view = LocalView.current
    
    // Handle key events at the view level
    DisposableEffect(view) {
        val listener = View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_DEL -> {
                        onBackspace()
                        true
                    }
                    KeyEvent.KEYCODE_ENTER -> {
                        onEnter()
                        true
                    }
                    KeyEvent.KEYCODE_SPACE -> {
                        onSpace()
                        true
                    }
                    KeyEvent.KEYCODE_ESCAPE -> {
                        onEscape()
                        true
                    }
                    KeyEvent.KEYCODE_TAB -> {
                        onTab()
                        true
                    }
                    else -> {
                        // Check if it's a printable character
                        if (event.isPrintingKey) {
                            val char = event.unicodeChar.toChar().toString()
                            onInput(char)
                            true
                        } else {
                            false
                        }
                    }
                }
            } else {
                false
            }
        }
        
        view.setOnKeyListener(listener)
        
        onDispose {
            view.setOnKeyListener(null)
        }
    }
}