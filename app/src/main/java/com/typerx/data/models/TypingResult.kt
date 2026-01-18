package com.typerx.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "typing_sessions")
data class TypingResult(
    @PrimaryKey val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    val language: String,
    val mode: String, // "time", "custom", "practice"
    val duration: Int, // in seconds
    val wpm: Double,
    val accuracy: Double,
    val errors: Int,
    val totalKeystrokes: Int,
    val correctKeystrokes: Int,
    val date: Date = Date(),
    val elapsedTime: Long // in milliseconds
)