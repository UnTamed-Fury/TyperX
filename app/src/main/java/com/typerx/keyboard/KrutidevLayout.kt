package com.typerx.keyboard

/**
 * Krutidev 10 Hindi keyboard layout mapping
 * This maps English keyboard keys to their corresponding Hindi characters
 */
object KrutidevLayout {
    // Mapping for the top row (QWERTY)
    private val topRow = mapOf(
        'q' to "द्वि", 'w' to "ब", 'e' to "ह", 'r' to "ग", 't' to "द", 'y' to "ज", 'u' to "ड़", 'i' to "प", 'o' to "र", 'p' to "क",
        'Q' to "द्वी", 'W' to "ब्", 'E' to "ह्", 'R' to "ग्", 'T' to "द्", 'Y' to "ज्", 'U' to "ड़्", 'I' to "प्", 'O' to "र्", 'P' to "क्"
    )
    
    // Mapping for the home row (ASDFGHJKL)
    private val homeRow = mapOf(
        'a' to "अ", 's' to "स", 'd' to "व", 'f' to "न", 'g' to "म", 'h' to "त", 'j' to "च", 'k' to "ट", 'l' to "ल",
        'A' to "आ", 'S' to "स्", 'D' to "व्", 'F' to "न्", 'G' to "म्", 'H' to "त्", 'J' to "च्", 'K' to "ट्", 'L' to "ल्"
    )
    
    // Mapping for the bottom row (ZXCVBNM)
    private val bottomRow = mapOf(
        'z' to "।", 'x' to "य", 'c' to "प", 'v' to "ष", 'b' to "ध", 'n' to "भ", 'm' to "औ",
        'Z' to "॰", 'X' to "य्", 'C' to "प्", 'V' to "ष्", 'B' to "ध्", 'N' to "भ्", 'M' to "ॐ"
    )
    
    // Special character mappings
    private val specialChars = mapOf(
        '1' to "१", '2' to "२", '3' to "३", '4' to "४", '5' to "५",
        '6' to "६", '7' to "७", '8' to "८", '9' to "९", '0' to "०",
        '-' to "ऋ", '=' to "ॠ",
        '[' to "ई", ']' to "ऊ",
        ';' to "इ", '\'' to "उ",
        ',' to "े", '.' to "ो", '/' to "ं",
        '`' to "औ", '~' to "ॐ",
        '!' to "१", '@' to "२", '#' to "३", '$' to "४", '%' to "५",
        '^' to "६", '&' to "७", '*' to "८", '(' to "९", ')' to "०",
        '_' to "ॠ", '+' to "ऌ",
        '{' to "ऐ", '}' to "औ",
        ':' to "ै", '"' to "ौ",
        '<' to "्", '>' to "।", '?' to "ँ"
    )
    
    // Vowel modifiers
    private val vowelModifiers = mapOf(
        'f' to "अ", 'F' to "आ",
        'u' to "ु", 'U' to "ू",
        'i' to "ि", 'I' to "ी",
        'o' to "ो", 'O' to "ौ",
        'a' to "ा", 'A' to "ा",
        'd' to "े", 'D' to "ै"
    )
    
    /**
     * Get the Hindi character for an English key
     */
    fun getHindiCharacter(key: Char): String {
        return topRow[key] ?: homeRow[key] ?: bottomRow[key] ?: specialChars[key] ?: key.toString()
    }
    
    /**
     * Convert English text to Hindi using Krutidev mapping
     */
    fun convertToHindi(input: String): String {
        val result = StringBuilder()
        
        for (char in input) {
            result.append(getHindiCharacter(char))
        }
        
        return result.toString()
    }
}