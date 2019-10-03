package org.learning.photogallery;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.annotation.Nullable;

public class PollService extends IntentService {
    private static final String TAG = "PollService";

    public static Intent newIntent(Context context) {
        return new Intent(context, PollService.class);
    }

    public PollService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (!isNetworkAvailableAndConnected()) {
            return;
        }
        Log.i(TAG, "Received an Intent: " + intent);
    }

    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailbale = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailbale && cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }
}
