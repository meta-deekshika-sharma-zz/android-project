package com.example.user15.intermediateapplication.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

public class MessageService extends Service {

    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStart(intent, startId);
        Toast.makeText(getBaseContext(), "Music started", Toast.LENGTH_SHORT).show();

        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT).show();
        player.stop();
    }
}
