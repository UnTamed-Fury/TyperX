package com.typerx.engine

import com.typerx.data.models.TypingResult
import com.typerx.data.repository.TypingResultRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class TypingEngine(
    private val repository: TypingResultRepository? = null
) {
    private var session: TypingSession? = null
    
    private val _sessionState = MutableStateFlow<TypingSession?>(null)
    val sessionState: StateFlow<TypingSession?> = _sessionState
    
    private val _timerState = MutableStateFlow(0L) // Time in seconds
    val timerState: StateFlow<Long> = _timerState
    
    private val _progressState = MutableStateFlow(0f) // Progress percentage
    val progressState: StateFlow<Float> = _progressState
    
    private var timerJob: Timer? = null
    
    fun startNewSession(text: String, language: String = "en", mode: String = "time", duration: Int = 60) {
        session = TypingSession(
            text = text,
            language = language,
            mode = mode,
            duration = duration
        ).apply {
            startTime = System.currentTimeMillis()
        }
        
        _sessionState.value = session
        
        // Start timer
        startTimer(duration)
    }
    
    private fun startTimer(duration: Int) {
        timerJob?.cancel()
        timerJob = Timer()
        
        val startTime = System.currentTimeMillis()
        _timerState.value = 0L
        
        timerJob?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000
                val remainingTime = maxOf(0L, duration - elapsedSeconds)
                
                _timerState.value = remainingTime
                
                // Update progress
                val progress = if (duration > 0) {
                    (elapsedSeconds.toFloat() / duration).coerceIn(0f, 1f)
                } else 0f
                _progressState.value = 1f - progress // Invert since we're counting down
                
                // End session when time is up
                if (remainingTime <= 0) {
                    endSession()
                    timerJob?.cancel()
                }
            }
        }, 0, 1000)
    }
    
    fun processInput(input: String) {
        session?.let { s ->
            val newTypedText = s.typedText + input
            val errors = AccuracyTracker.countErrors(s.text, newTypedText)
            val correctChars = newTypedText.length - errors
            val accuracy = AccuracyTracker.calculateAccuracy(correctChars, newTypedText.length)
            val timeElapsed = (System.currentTimeMillis() - s.startTime) / 1000
            val wpm = WpmCalculator.calculateWpm(correctChars, timeElapsed)
            
            session = s.copy(
                typedText = newTypedText,
                errors = errors,
                totalKeystrokes = newTypedText.length,
                correctKeystrokes = correctChars,
                accuracy = accuracy,
                wpm = wpm
            )
            
            _sessionState.value = session
        }
    }
    
    fun backspace(count: Int = 1) {
        session?.let { s ->
            val newTypedText = if (s.typedText.length >= count) {
                s.typedText.substring(0, s.typedText.length - count)
            } else {
                ""
            }
            
            val errors = AccuracyTracker.countErrors(s.text, newTypedText)
            val correctChars = newTypedText.length - errors
            val accuracy = AccuracyTracker.calculateAccuracy(correctChars, newTypedText.length)
            val timeElapsed = (System.currentTimeMillis() - s.startTime) / 1000
            val wpm = WpmCalculator.calculateWpm(correctChars, timeElapsed)
            
            session = s.copy(
                typedText = newTypedText,
                errors = errors,
                totalKeystrokes = newTypedText.length,
                correctKeystrokes = correctChars,
                accuracy = accuracy,
                wpm = wpm
            )
            
            _sessionState.value = session
        }
    }
    
    fun endSession() {
        session?.let { s ->
            val endTime = System.currentTimeMillis()
            session = s.copy(endTime = endTime)
            _sessionState.value = session
            
            // Save result to database if repository is available
            if (repository != null) {
                val result = TypingResult(
                    text = s.text,
                    language = s.language,
                    mode = s.mode,
                    duration = s.duration,
                    wpm = s.wpm,
                    accuracy = s.accuracy,
                    errors = s.errors,
                    totalKeystrokes = s.totalKeystrokes,
                    correctKeystrokes = s.correctKeystrokes,
                    elapsedTime = endTime - s.startTime
                )
                
                CoroutineScope(Dispatchers.IO).launch {
                    repository.insertResult(result)
                }
            }
        }
        
        timerJob?.cancel()
        timerJob = null
    }
    
    fun resetSession() {
        session = null
        _sessionState.value = null
        _timerState.value = 0L
        _progressState.value = 0f
        timerJob?.cancel()
        timerJob = null
    }
    
    fun getSession(): TypingSession? = session
}