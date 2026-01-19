package fury.typerx.engine

import kotlin.math.roundToInt

class AccuracyTracker {

    companion object {
        /**
         * Calculate accuracy percentage
         */
        fun calculateAccuracy(correctChars: Int, totalChars: Int): Double {
            if (totalChars == 0) return 100.0
            return ((correctChars.toDouble() / totalChars) * 100).roundToInt().toDouble()
        }

        /**
         * Count errors in typed text compared to reference text
         */
        fun countErrors(referenceText: String, typedText: String): Int {
            var errors = 0
            val minLength = minOf(referenceText.length, typedText.length)

            // Count character mismatches
            for (i in 0 until minLength) {
                if (referenceText[i] != typedText[i]) {
                    errors++
                }
            }

            // Count extra characters if typed text is longer
            if (typedText.length > referenceText.length) {
                errors += typedText.length - referenceText.length
            }

            // Count missing characters if typed text is shorter
            if (typedText.length < referenceText.length) {
                errors += referenceText.length - typedText.length
            }

            return errors
        }
    }
}