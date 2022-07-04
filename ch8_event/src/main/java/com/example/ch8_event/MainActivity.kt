package com.example.ch8_event

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var initTime = 0L
    var pauseTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            startButton.setOnClickListener {
                chronometer.apply {
                    base = SystemClock.elapsedRealtime() + pauseTime
                    start()
                }
                stopButton.isEnabled = true
                resetButton.isEnabled = true
                startButton.isEnabled = false
            }

            stopButton.setOnClickListener {
                pauseTime = chronometer.base - SystemClock.elapsedRealtime()
                chronometer.stop()
                stopButton.isEnabled = false
                resetButton.isEnabled = true
                startButton.isEnabled = true
            }

            resetButton.setOnClickListener {
                pauseTime = 0L
                binding.chronometer.base = SystemClock.elapsedRealtime()
                chronometer.stop()
                stopButton.isEnabled = false
                resetButton.isEnabled = false
                startButton.isEnabled = true
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 뒤로가기 버튼을 한 번 더 누르세요.", Toast.LENGTH_LONG).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}