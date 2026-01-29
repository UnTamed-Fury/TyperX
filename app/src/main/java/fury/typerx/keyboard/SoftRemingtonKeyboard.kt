package fury.typerx.keyboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SoftRemingtonKeyboard(
    onKeyPress: (Char) -> Unit,
    onBackspace: () -> Unit
) {
    // Simplified Soft Keyboard for Remington
    // Rows showing key mappings
    Column(modifier = Modifier.padding(8.dp)) {
        Row {
            // Example Row
            KeyButton('q', "f (ि)", onKeyPress)
            KeyButton('w', "d (्)", onKeyPress)
            KeyButton('e', "m (म)", onKeyPress)
            KeyButton('r', "t (त)", onKeyPress)
            KeyButton('t', "j (ज)", onKeyPress)
            KeyButton('y', "l (ल)", onKeyPress)
            KeyButton('u', "n (न)", onKeyPress)
            KeyButton('i', "p (प)", onKeyPress)
            KeyButton('o', "v (व)", onKeyPress)
            KeyButton('p', "c (च)", onKeyPress)
        }
        Row {
            Button(onClick = onBackspace) {
                Text("Backspace")
            }
        }
    }
}

@Composable
fun KeyButton(char: Char, label: String, onKeyPress: (Char) -> Unit) {
    Button(onClick = { onKeyPress(char) }, modifier = Modifier.padding(2.dp)) {
        Text(label)
    }
}
