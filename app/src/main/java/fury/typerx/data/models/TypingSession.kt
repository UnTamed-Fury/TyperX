package fury.typerx.data.models

data class TypingSession(
    val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    var startTime: Long = 0L,
    var endTime: Long = 0L,
    var typedText: String = "",
    var errors: Int = 0,
    var totalKeystrokes: Int = 0,
    var correctKeystrokes: Int = 0,
    var wpm: Double = 0.0,
    var accuracy: Double = 100.0,
    val language: String = "en",
    val mode: String = "time",
    val duration: Int = 60 // in seconds
)