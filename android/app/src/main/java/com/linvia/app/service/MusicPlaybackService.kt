package com.linvia.app.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.ServiceCompat
import androidx.core.app.ForegroundServiceCompat
import com.linvia.app.model.Track

class MusicPlaybackService : Service() {

    private var currentTrack: Track? = null
    private var isPlaying = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        when (action) {
            "ACTION_PLAY" -> play()
            "ACTION_PAUSE" -> pause()
            "ACTION_STOP" -> stopSelf()
        }
        return START_STICKY
    }

    private fun play() {
        isPlaying = true
        // Start foreground with media notification
    }

    private fun pause() {
        isPlaying = false
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
