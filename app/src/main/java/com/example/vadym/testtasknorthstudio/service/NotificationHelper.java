package com.example.vadym.testtasknorthstudio.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.vadym.testtasknorthstudio.R;

/**
 * Created by Vadym on 23.02.2018.
 */

public class NotificationHelper extends ContextWrapper {

    private static final String CHANNEL_ID = "some_id";
    private static final String CHANNEL_NAME = "some channel";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.setLightColor(R.color.colorPrimary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public NotificationCompat.Builder getBuilder(String title, String message) {
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_action_name)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}
