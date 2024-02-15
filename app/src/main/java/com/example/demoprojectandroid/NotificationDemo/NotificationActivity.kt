package com.example.demoprojectandroid.NotificationDemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.demoprojectandroid.databinding.ActivityNotificationBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.onesignal.OSNotificationOpenedResult
import com.onesignal.OneSignal


class NotificationActivity : AppCompatActivity() {

    lateinit var binding: ActivityNotificationBinding
    private val CHANNEL_ID = "my_channel_id"
    private val CHANNEL_NAME = "My Channel"
    private val CHANNEL_DESCRIPTION = "My Channel Description"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener<String?> { task ->
                if (!task.isSuccessful) {
                    Log.e("TAG", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                // Send the device token to your server or store it in SharedPreferences
                Log.e("TAG", "Device token: " + task.result)
            })

        binding.btnNotification.setOnClickListener(View.OnClickListener {

            showNotification(this,"This is notificaiton title","This is Tets MessageThis is Tets MessageThis is Tets MessageThis is Tets MessageThis is Tets MessageThis is Tets MessageThis is Tets MessageThis is Tets MessageThis is Tets MessageThis is Tets Message")
        })

        OneSignalNotification();
    }

    private fun OneSignalNotification() {

    }

    fun showNotification(context: Context, title: String, message: String) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel for Android Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_DESCRIPTION
            notificationManager.createNotificationChannel(channel)
        }

        // Create a notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Show the notification
        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

}