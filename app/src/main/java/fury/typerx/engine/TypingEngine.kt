package fury.typerx.engine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TypingState(
    val targetText: String = "",
    val typedText: String = "",
    val startTime: Long = 0,
    val isRunning: Boolean = false,
    val isFinished: Boolean = false,
    val wpm: Int = 0,
    val accuracy: Int = 100,
    val mistakes: Int = 0,
    val timeRemaining: Int = 0, // Seconds
    val totalTime: Int = 0
)

class TypingEngine {
    private val _state = MutableStateFlow(TypingState())
    val state: StateFlow<TypingState> = _state.asStateFlow()
    
    private val accuracyTracker = AccuracyTracker()
    private val stateMachine = KrutidevStateMachine()
    
    private var timerJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    fun startSession(text: String, durationSeconds: Int) {
        timerJob?.cancel()
        _state.update {
            TypingState(
                targetText = text,
                typedText = "",
                startTime = System.currentTimeMillis(),
                isRunning = true,
                isFinished = false,
                timeRemaining = durationSeconds,
                totalTime = durationSeconds
            )
        }
        
        startTimer()
    }

    private fun startTimer() {
        timerJob = scope.launch {
            while (_state.value.isRunning && _state.value.timeRemaining > 0) {
                delay(100) // Check more frequently
                _state.update { s ->
                    val elapsed = (System.currentTimeMillis() - s.startTime) / 1000
                    val newTime = (s.totalTime - elapsed).toInt()
                    
                    // Prevent negative time
                    val safeTime = if (newTime < 0) 0 else newTime
                    s.copy(timeRemaining = safeTime)
                }
                
                if (_state.value.timeRemaining <= 0) {
                    finishSession()
                } else {
                    calculateStats()
                }
            }
        }
    }

    fun handleKeyPress(char: Char) {
        if (!_state.value.isRunning) return

        _state.update { s ->
            val newTyped = stateMachine.processInput(s.typedText, char)
            s.copy(typedText = newTyped)
        }
        calculateStats()
    }

    fun handleBackspace() {
        if (!_state.value.isRunning) return
        
        _state.update { s ->
            val newTyped = stateMachine.handleBackspace(s.typedText)
            s.copy(typedText = newTyped)
        }
        calculateStats()
    }

    private fun finishSession() {
        _state.update { it.copy(isRunning = false, isFinished = true) }
        calculateStats()
        timerJob?.cancel()
    }

    private fun calculateStats() {
        val s = _state.value
        val elapsedTimeMillis = (s.totalTime - s.timeRemaining) * 1000L
        
        // Avoid divide by zero
        val safeTime = if (elapsedTimeMillis < 1000) 1000L else elapsedTimeMillis

        val wpm = accuracyTracker.calculateWpm(s.typedText.length, safeTime)
        val accuracy = accuracyTracker.calculateAccuracy(s.targetText, s.typedText)
        val errors = accuracyTracker.countErrors(s.targetText, s.typedText)

        _state.update { it.copy(wpm = wpm, accuracy = accuracy, mistakes = errors) }
    }
    
    fun reset() {
        timerJob?.cancel()
        _state.update { TypingState() }
    }
}
