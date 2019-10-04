package org.learning.photogallery;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.List;

public class PollService {
    private static final String TAG = "PollService";

    public static boolean isPollServiceOn(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            return PollServiceJS.isJobScheduled(context);
        } else{
            return PollServiceIS.isServiceAlarmOn(context);
        }
    }

    public static void setPollService(Context context, boolean isOn) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            PollServiceJS.setJobService(context, isOn);
        } else{
            PollServiceIS.setAlarmService(context, isOn);
        }
    }

    public static void checkNewImages(Context context, Resources resources) {
        String query = QueryPreferences.getStoredQuery(context);
        String lastResultId = QueryPreferences.getLastResultId(context);

        List<GalleryItem> items;

        if (query == null) {
            items = new FlickrFetchr().fetchRecentPhotos();
        } else {
            items = new FlickrFetchr().searchPhotos(query);
        }

        String resultId = items.get(0).getId();
        if (resultId.equals(lastResultId)) {
            Log.i(TAG, "Got an old result: " + resultId);
        } else {
            Log.i(TAG, "Got a new result: " + resultId);

            Intent i = PhotGalleryActivity.newIntent(context);

            PendingIntent pi = PendingIntent.getService(context, 0, i, 0);

            Notification notification = new NotificationCompat.Builder(context)
                    .setTicker(resources.getString(R.string.new_pictures_title))
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setContentTitle(resources.getString(R.string.new_pictures_text))
                    .setContentText(resources.getString(R.string.new_pictures_title))
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(0, notification);
        }
        QueryPreferences.setSLastResultId(context, resultId);
    }
}
