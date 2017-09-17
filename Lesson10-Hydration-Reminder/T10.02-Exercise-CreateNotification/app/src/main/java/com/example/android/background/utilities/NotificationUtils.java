package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    public static final int NOTIFICATION_ID = 7331;
    public static final int START_MAIN_ACTIVITY_PENDING_INTENT_ID = 1337;

    public static void sendNotification(Context context) {
        String notificationText = context.getString(R.string.charging_reminder_notification_body);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(getLargeNotificationIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(notificationText)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationText))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(createPendingIntentForMainActivity(context))
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    private static PendingIntent createPendingIntentForMainActivity(Context context) {
        Intent startMainActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, START_MAIN_ACTIVITY_PENDING_INTENT_ID, startMainActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap getLargeNotificationIcon(Context context) {
        Resources resources = context.getResources();
        return BitmapFactory.decodeResource(resources, R.drawable.ic_local_drink_black_24px);
    }

}
