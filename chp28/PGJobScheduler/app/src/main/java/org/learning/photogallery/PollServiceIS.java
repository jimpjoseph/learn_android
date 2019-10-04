package org.learning.photogallery;


import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PollServiceIS extends IntentService {
    private static final String TAG = "PollServiceIS";

    //Set interval to 1 minute.
    private static final long POLL_INTERVAL_MS = TimeUnit.MINUTES.toMillis(15);

    public static Intent newIntent(Context context) {
        return new Intent(context, PollServiceIS.class);
    }

    public static void setAlarmService(Context context, boolean isOn) {
        Intent i = PollServiceIS.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (isOn) {
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(), POLL_INTERVAL_MS, pi);
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }

    public static boolean isServiceAlarmOn(Context context) {
        Intent i = PollServiceIS.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

    public PollServiceIS() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (!isNetworkAvailableAndConnected()) {
            return;
        }
        Log.i(TAG, "Received an Intent: " + intent);

        PollService.checkNewImages(this, getResources());
    }

    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailbale = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailbale && cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }
}
