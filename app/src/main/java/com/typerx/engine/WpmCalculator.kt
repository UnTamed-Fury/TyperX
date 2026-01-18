package com.typerx.engine

import kotlin.math.roundToInt

class WpmCalculator {
    
    companion object {
        /**
         * Calculate Words Per Minute (WPM)
         * Standard: 1 word = 5 characters (including spaces)
         */
        fun calculateWpm(typedCharacters: Int, timeInSeconds: Long): Double {
            if (timeInSeconds == 0L) return 0.0
            
            val minutes = timeInSeconds.toDouble() / 60.0
            val words = typedCharacters.toDouble() / 5.0
            return (words / minutes).roundToInt().toDouble()
        }
        
        /**
         * Calculate raw WPM (includes errors)
         */
        fun calculateRawWpm(totalTypedCharacters: Int, timeInSeconds: Long): Double {
            if (timeInSeconds == 0L) return 0.0
            
            val minutes = timeInSeconds.toDouble() / 60.0
            val words = totalTypedCharacters.toDouble() / 5.0
            return (words / minutes).roundToInt().toDouble()
        }
    }
}