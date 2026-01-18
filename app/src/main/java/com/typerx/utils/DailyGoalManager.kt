package com.typerx.utils

import android.content.Context
import android.content.SharedPreferences

class DailyGoalManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("daily_goal", Context.MODE_PRIVATE)
    
    companion object {
        private const val GOAL_KEY = "daily_typing_goal"
        private const val COMPLETED_TODAY_KEY = "completed_today"
        private const val STREAK_KEY = "current_streak"
    }
    
    fun setDailyGoal(minutes: Int) {
        prefs.edit().putInt(GOAL_KEY, minutes).apply()
    }
    
    fun getDailyGoal(): Int {
        return prefs.getInt(GOAL_KEY, 15) // Default to 15 minutes
    }
    
    fun incrementCompletedSession(minutes: Int) {
        val completedToday = prefs.getInt(COMPLETED_TODAY_KEY, 0)
        prefs.edit().putInt(COMPLETED_TODAY_KEY, completedToday + minutes).apply()
        
        // Update streak if needed
        updateStreak()
    }
    
    fun getCompletedToday(): Int {
        return prefs.getInt(COMPLETED_TODAY_KEY, 0)
    }
    
    fun getStreak(): Int {
        return prefs.getInt(STREAK_KEY, 0)
    }
    
    private fun updateStreak() {
        // Logic to update daily streak would go here
        // This is a simplified version
    }
    
    fun resetDailyProgress() {
        prefs.edit().putInt(COMPLETED_TODAY_KEY, 0).apply()
    }
}