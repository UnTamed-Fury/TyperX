package com.typerx.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi

class FeedbackManager(private val context: Context) {
    private var soundPool: SoundPool? = null
    private var keyPressSoundId: Int? = null
    private var errorSoundId: Int? = null
    private val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    
    init {
        initializeSoundPool()
    }
    
    private fun initializeSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            
            soundPool = SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build()
            
            // In a real app, we would load actual sound files
            // For now, we'll just note that sounds would be loaded here
        } else {
            @Suppress("DEPRECATION")
            soundPool = SoundPool(2, android.media.AudioManager.STREAM_MUSIC, 0)
        }
    }
    
    fun playKeyPressSound() {
        keyPressSoundId?.let { soundId ->
            soundPool?.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }
    
    fun playErrorSound() {
        errorSoundId?.let { soundId ->
            soundPool?.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }
    
    fun triggerHapticFeedback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE)
            )
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(20)
        }
    }
    
    fun release() {
        soundPool?.release()
    }
}