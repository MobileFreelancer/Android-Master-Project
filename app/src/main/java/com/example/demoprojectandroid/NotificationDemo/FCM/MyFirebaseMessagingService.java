package com.example.demoprojectandroid.NotificationDemo.FCM;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        // Send the device token to your server or store it in SharedPreferences
        Log.d("TAG", "FCM Refreshed token: " + token);

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Log.e("TAG", "onMessageReceived: " + new Gson().toJson(message.getNotification()));


    //        if (!message.getNotification().getBody().isEmpty() && !message.getNotification().getTitle().isEmpty()) {
//            // Handle data payload
//            String title = message.getNotification().getTitle();
//            String body = message.getNotification().getBody();
//
//            String channelId = getString(R.string.default_notification_channel_id);
//            Intent intent = new Intent(this, NotificationlistActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.putExtra("isNotification", "yes"); // Add the extra parameter here
//
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
//
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
//                    .setContentTitle(title)
//                    .setContentText(body)
//                    .setSmallIcon(R.drawable.notification_icon)
//                    .setPriority(NotificationCompat.PRIORITY_MAX)
//                    .setCategory(NotificationCompat.CATEGORY_CALL)
//                    .setFullScreenIntent(pendingIntent, true)
//                    .setAutoCancel(true);
//
//            if (!Constants.isInNotificationActivity) {
//                notificationBuilder.setFullScreenIntent(pendingIntent, true);
//            }
//
//
//            // NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//            // Since Android Oreo, notification channels are required
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////                NotificationChannel channel = new NotificationChannel(channelId, getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
////                notificationManager.createNotificationChannel(channel);
////            }
////
////            notificationManager.notify(0, notificationBuilder.build());
//
//
//            // Since Android Oreo, notification channels are required
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel channel = new NotificationChannel(channelId, getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
//                notificationManagerCompat.createNotificationChannel(channel);
//            }
//
//            try {
//                // Check if the necessary permission is available
//                if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
//                    notificationManagerCompat.notify(0, notificationBuilder.build());
//                } else {
//                    // Handle the case when the permission is not granted
//                    // You can show a toast, dialog, or request the permission from the user
//                }
//            } catch (SecurityException e) {
//                // Handle the SecurityException
//            }
//
//
//        }


    }
}
