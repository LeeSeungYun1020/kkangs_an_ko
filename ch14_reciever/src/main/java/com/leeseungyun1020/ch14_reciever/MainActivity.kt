package com.leeseungyun1020.ch14_reciever

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leeseungyun1020.ch14_reciever.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))?.apply {
            when (getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
                BatteryManager.BATTERY_STATUS_CHARGING -> {
                    when (getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {
                        BatteryManager.BATTERY_PLUGGED_AC -> {
                            binding.chargingResultView.text = "AC Plugged"
                            binding.chargingImageView.setImageResource(R.drawable.ac)
                        }
                        BatteryManager.BATTERY_PLUGGED_USB -> {
                            binding.chargingResultView.text = "USB Plugged"
                            binding.chargingImageView.setImageResource(R.drawable.usb)
                        }
                        BatteryManager.BATTERY_PLUGGED_WIRELESS -> {
                            binding.chargingResultView.text = "Wireless Plugged"
                        }
                        else -> {
                            binding.chargingResultView.text = "Charging"
                        }
                    }
                }
                else -> {
                    binding.chargingResultView.text = "Not Plugged"
                }
            }

            val level = getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val batteryPct = (level / scale.toFloat() * 100).toInt()
            binding.percentResultView.text = "$batteryPct%"
        }

        binding.button.setOnClickListener {
            sendBroadcast(Intent(this, MyReceiver::class.java))
        }
    }
}