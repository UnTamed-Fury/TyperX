package fury.typerx.keyboard

import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.type

class PhysicalKeyHandler(
    private val onChar: (Char) -> Unit,
    private val onBackspace: () -> Unit
) {
    fun handle(event: KeyEvent): Boolean {
        if (event.type == KeyEventType.KeyDown) {
            val char = RemingtonKeyMapper.mapKeyEvent(event)
            if (char != null) {
                onChar(char)
                return true
            } else if (event.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_DEL) {
                onBackspace()
                return true
            }
        }
        return false
    }
}
