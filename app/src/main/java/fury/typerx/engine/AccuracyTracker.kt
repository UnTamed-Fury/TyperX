package fury.typerx.engine

class AccuracyTracker {
    
    fun calculateWpm(charCount: Int, timeMillis: Long): Int {
        if (timeMillis <= 0) return 0
        val minutes = timeMillis / 60000.0
        // Standard WPM definition: 5 characters = 1 word
        return ((charCount / 5.0) / minutes).toInt()
    }

    fun calculateAccuracy(target: String, typed: String): Int {
        if (typed.isEmpty()) return 100
        
        var errors = 0
        val lengthToCheck = minOf(target.length, typed.length)
        
        for (i in 0 until lengthToCheck) {
            if (target[i] != typed[i]) {
                errors++
            }
        }
        
        // Count extra characters as errors?
        // CPCT rules usually count specific error types.
        // For simplicity: (Total - Errors) / Total * 100
        
        return ((typed.length - errors).toDouble() / typed.length * 100).toInt().coerceIn(0, 100)
    }
    
    fun countErrors(target: String, typed: String): Int {
        var errors = 0
        val lengthToCheck = minOf(target.length, typed.length)
        for (i in 0 until lengthToCheck) {
            if (target[i] != typed[i]) {
                errors++
            }
        }
        // Count extra typed characters as errors
        if (typed.length > target.length) {
            errors += (typed.length - target.length)
        }
        return errors
    }
}
