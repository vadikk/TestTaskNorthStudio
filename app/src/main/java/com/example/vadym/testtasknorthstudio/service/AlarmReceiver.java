package com.example.vadym.testtasknorthstudio.service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.vadym.testtasknorthstudio.R;
import com.example.vadym.testtasknorthstudio.activities.MainActivity;

/**
 * Created by Vadym on 24.02.2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private static final int ID = 101;

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent == null || !intent.hasExtra("message"))
            return;

        String message = intent.getStringExtra("message");

        Intent contectIntent = new Intent(context.getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, contectIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder builder = notificationHelper.getBuilder(context.getString(R.string.notification), message);
        builder.setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationHelper.getManager().notify(ID, builder.build());
    }
}
