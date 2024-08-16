package com.example.banquemisr.ui.screens.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.banquemisr.R



fun notificationContent(textTitle: String, textContent: String,context: Context) {
    // Ensure the notification channel is created (only necessary for API 26+)
    createNotificationChannel(context)
    // Build the notification
    val notificationBuilder = NotificationCompat.Builder(context, "1")
        .setSmallIcon(R.drawable.banquemisr_smallicon) // Your icon
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true) // Dismiss notification on click
    // Show the notification
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(1, notificationBuilder.build()) // '1' is the notification ID
}



fun createNotificationChannel(context: Context) {

    // Create the NotificationChannel, only on API 26+ (Oreo and above)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Transfer Notifications"
        val descriptionText = "Notifications for successful transfers"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("1", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
