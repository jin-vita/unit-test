package com.jinvita.mysoundplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinvita.mysoundplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mediaPlayer1 by lazy {
        MediaPlayer.create(this, R.raw.traffic).apply { setOnCompletionListener { start() } }
    }
    private val mediaPlayer2 by lazy {
        MediaPlayer.create(this, R.raw.traffic2).apply { setOnCompletionListener { start() } }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOne.setOnClickListener { playOrPauseAudio11() }
        binding.buttonTwo.setOnClickListener { playOrPauseAudio22() }
    }

    private fun playOrPauseAudio11() {
        if (mediaPlayer1.isPlaying) {
            mediaPlayer1.pause()
            binding.buttonOne.text = "1번 일시 정지"
        } else {
            mediaPlayer1.start()
            binding.buttonOne.text = "1번 반복 재생 중"
        }

    }

    private fun playOrPauseAudio22() {
        if (mediaPlayer2.isPlaying) {
            mediaPlayer2.stop()
            mediaPlayer2.prepare()
            binding.buttonTwo.text = "2번 정지"
        } else {
            mediaPlayer2.start()
            binding.buttonTwo.text = "2번 반복 재생 중"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer1.release()
        mediaPlayer2.release()
    }
}