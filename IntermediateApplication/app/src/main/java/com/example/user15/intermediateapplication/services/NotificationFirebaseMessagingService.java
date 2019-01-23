package com.example.user15.intermediateapplication.services;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.user15.intermediateapplication.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {

        Log.d("12345", "token  " + s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        sendNotification(title, body);
    }

    private void sendNotification(String title, String body) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getString(R.string.channel_id));
        notificationBuilder.setSmallIcon(R.drawable.ic_people_black_24dp)
                .setContentTitle(title)
                .setContentText(body);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0, notificationBuilder.build());
    }
}
