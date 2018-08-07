/*
package com.articles.newsfeed.model.network;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import com.articles.newsfeed.R;
import com.articles.newsfeed.presenter.GetArticlePresenter;
import com.articles.newsfeed.view.GetArticleActivity;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NewsSyncJob extends Job {

    public static final String TAG = "job_demo_tag";

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        // run your job here
        Intent intent = new Intent(getContext(), SeparateProcessService.class);
        getContext().startService(intent);


        return Result.SUCCESS;
    }

    private void sendNotification() {

     */
/*   NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int icon = R.drawable.notification_icon;
        CharSequence notiText = "Your notification from the service";
        long meow = System.currentTimeMillis();

        Notification notification = new Notification(icon, notiText, meow);

        Context context = getApplicationContext();
        CharSequence contentTitle = "Your notification";
        CharSequence contentText = "Some data has arrived!";
        Intent notificationIntent = new Intent(this, GetArticleActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

        int SERVER_DATA_RECEIVED = 1;
        notificationManager.notify(SERVER_DATA_RECEIVED, notification);*//*


       */
/* NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this).setContentTitle("New Article Received")
                .setStyle(new NotificationCompat.BigTextStyle()
                .setSmallIcon())*//*


    }

    public class SeparateProcessService extends IntentService {
        public SeparateProcessService() {
            super("SeparateProcessService");
        }

        @Override
        protected void onHandleIntent(Intent intent) {
            // do work
            GetArticlePresenter getArticlePresenter=new GetArticlePresenter();
            getArticlePresenter.getArticles();
            sendNotification();
        }
    }



    public static void schedulePeriodicJob() {
        Set<JobRequest> jobRequests = JobManager.instance().getAllJobRequestsForTag(NewsSyncJob.TAG);
        if (!jobRequests.isEmpty()) {
            return;
        }
        int jobId = new JobRequest.Builder(NewsSyncJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setRequirementsEnforced(true)
                .build()
                .schedule();
    }

}
*/
