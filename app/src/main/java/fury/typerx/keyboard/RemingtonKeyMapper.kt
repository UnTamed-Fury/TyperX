package fury.typerx.keyboard

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.utf16CodePoint
import androidx.compose.ui.input.key.isShiftPressed

object RemingtonKeyMapper {

    // Map basic QWERTY keys to Krutidev (ASCII) characters
    // This is a simplified mapping for Krutidev 010
    // Source: Standard Krutidev layout charts
    
    fun mapKeyEvent(event: KeyEvent): Char? {
        val isShift = event.isShiftPressed
        val key = event.key
        
        // Handling specific keys manually due to lack of standard A-Z enum iteration in this context
        // and to be precise.
        
        // This mapping assumes a US QWERTY physical keyboard
        
        return when (key) {
            Key.Q -> if (isShift) 'Q' else 'q' // 'Q' ->Ph 'q' -> matra
            Key.W -> if (isShift) 'W' else 'w'
            Key.E -> if (isShift) 'E' else 'e'
            Key.R -> if (isShift) 'R' else 'r'
            Key.T -> if (isShift) 'T' else 't'
            Key.Y -> if (isShift) 'Y' else 'y'
            Key.U -> if (isShift) 'U' else 'u'
            Key.I -> if (isShift) 'I' else 'i'
            Key.O -> if (isShift) 'O' else 'o'
            Key.P -> if (isShift) 'P' else 'p'
            Key.A -> if (isShift) 'A' else 'a'
            Key.S -> if (isShift) 'S' else 's'
            Key.D -> if (isShift) 'D' else 'd'
            Key.F -> if (isShift) 'F' else 'f'
            Key.G -> if (isShift) 'G' else 'g'
            Key.H -> if (isShift) 'H' else 'h'
            Key.J -> if (isShift) 'J' else 'j'
            Key.K -> if (isShift) 'K' else 'k'
            Key.L -> if (isShift) 'L' else 'l'
            Key.Z -> if (isShift) 'Z' else 'z'
            Key.X -> if (isShift) 'X' else 'x'
            Key.C -> if (isShift) 'C' else 'c'
            Key.V -> if (isShift) 'V' else 'v'
            Key.B -> if (isShift) 'B' else 'b'
            Key.N -> if (isShift) 'N' else 'n'
            Key.M -> if (isShift) 'M' else 'm'
            
            Key.LeftBracket -> if (isShift) '{' else '['
            Key.RightBracket -> if (isShift) '}' else ']'
            Key.Backslash -> if (isShift) '|' else '\'
            Key.Semicolon -> if (isShift) ':' else ';'
            Key.Apostrophe -> if (isShift) '"' else "'"
            Key.Comma -> if (isShift) '<' else ','
            Key.Period -> if (isShift) '>' else '.'
            Key.Slash -> if (isShift) '?' else '/'
            Key.Grave -> if (isShift) '~' else '`'
            Key.Minus -> if (isShift) '_' else '-'
            Key.Equals -> if (isShift) '+' else '='
            
            Key.Spacebar -> ' '
            
            // Numbers
            Key.One -> if (isShift) '!' else '1'
            Key.Two -> if (isShift) '@' else '2'
            Key.Three -> if (isShift) '#' else '3'
            Key.Four -> if (isShift) '$' else '4'
            Key.Five -> if (isShift) '%' else '5'
            Key.Six -> if (isShift) '^' else '6'
            Key.Seven -> if (isShift) '&' else '7'
            Key.Eight -> if (isShift) '*' else '8'
            Key.Nine -> if (isShift) '(' else '9'
            Key.Zero -> if (isShift) ')' else '0'
            
            else -> null
        }
    }
}
