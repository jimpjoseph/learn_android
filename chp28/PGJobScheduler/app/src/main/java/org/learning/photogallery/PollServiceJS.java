package org.learning.photogallery;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;


@TargetApi(21)
public class PollServiceJS extends JobService {
    private static final String TAG = "PollSeriveJS";

    private static final long SChEDULED_PERIOD = 1000 * 60 * 15;

    private PollTask mCurrentTask;

    private static final int JOBID = 1;

    public static boolean isJobScheduled(Context context) {
        boolean hasBeenScheduled = false;

        JobScheduler scheduler = (JobScheduler)
                context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        for (JobInfo jobInfo : scheduler.getAllPendingJobs()) {
            if (jobInfo.getId() == JOBID) {
                hasBeenScheduled = true;
            }
        }
        Log.i(TAG, "Job scheudled: " +hasBeenScheduled);

        return hasBeenScheduled;
    }

    public static void setJobService(Context context, boolean isOn) {
        boolean isScheduled = isJobScheduled(context);

        // If already scheduled and the request is to turn it on or.
        // If not scheduled and the request is to turn it off
        // Then do nothing and return.
        if ((isScheduled && isOn) || (!isScheduled && !isOn)) {
            Log.w(TAG, "Job scheudling - no action taken: ");
            return;
        }

        JobScheduler scheduler = (JobScheduler)
                context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        if (isOn) {
            JobInfo jobInfo;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                jobInfo = new JobInfo.Builder(
                        JOBID, new ComponentName(context, PollServiceJS.class))
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                        .setMinimumLatency(SChEDULED_PERIOD)
                        .setPersisted(true)
                        .build();
            } else {
                jobInfo = new JobInfo.Builder(
                        JOBID, new ComponentName(context, PollServiceJS.class))
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                        .setPeriodic(SChEDULED_PERIOD)
                        .setPersisted(true)
                        .build();
            }
            scheduler.schedule(jobInfo);
            Log.i(TAG, "Job Scheduled");
        } else {
            scheduler.cancel(JOBID);
            Log.i(TAG, "Job Cancelled");
        }
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        mCurrentTask = new PollTask(this);
        mCurrentTask.execute(jobParameters);
        Log.i(TAG, "On Start Job");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (mCurrentTask != null) {
            mCurrentTask.cancel(true);
        }
        Log.i(TAG, "On Stop Job");
        return false;
    }

    private class PollTask extends AsyncTask<JobParameters, Void, Void> {
        private JobService mJS;
        public PollTask(JobService js) {
            mJS = js;
        }

        @Override
        protected Void doInBackground(JobParameters... jobParameters) {
            JobParameters jobParams = jobParameters[0];
            PollService.checkNewImages(mJS,getResources());
            jobFinished(jobParams, false);
            return null;
        }
    }

}
