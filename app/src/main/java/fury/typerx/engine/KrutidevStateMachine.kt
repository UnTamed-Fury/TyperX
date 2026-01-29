package fury.typerx.engine

class KrutidevStateMachine {
    
    // In a full implementation, this state machine would handle complex glyph formations.
    // For Remington (legacy) input where visual order = input order (mostly),
    // we simply append.
    // However, we prepare this structure to handle potential 'half-character' logic if needed.

    fun processInput(currentText: String, inputChar: Char): String {
        return currentText + inputChar
    }

    fun handleBackspace(currentText: String): String {
        // TODO: Implement logic to remove full logical glyph clusters for CPCT compliance
        if (currentText.isNotEmpty()) {
            return currentText.dropLast(1)
        }
        return currentText
    }
}
