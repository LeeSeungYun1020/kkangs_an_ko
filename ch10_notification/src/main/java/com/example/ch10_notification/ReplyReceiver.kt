package com.example.ch10_notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.RemoteInput

val KEY_TEXT_REPLY = "key_text_reply"
val MESSAGE_NOTIFICATION_ID = 11

class ReplyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val replyText = RemoteInput.getResultsFromIntent(intent)?.getString(KEY_TEXT_REPLY)
        Toast.makeText(context, replyText, Toast.LENGTH_LONG).show()

        val manager =
            context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(MESSAGE_NOTIFICATION_ID)
    }
}