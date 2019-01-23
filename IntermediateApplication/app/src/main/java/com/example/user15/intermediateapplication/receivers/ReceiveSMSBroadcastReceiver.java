package com.example.user15.intermediateapplication.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.user15.intermediateapplication.services.MessageService;

public class ReceiveSMSBroadcastReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String KEYWORD = "Play Music Player";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (SMS_RECEIVED.equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];

                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                if (messages.length > -1) {
                    Toast.makeText(context, "" + messages[0].getMessageBody(), Toast.LENGTH_SHORT).show();

                    if (messages[0].getMessageBody().toLowerCase().contains(KEYWORD.toLowerCase())) {
                        Intent serviceIntent = new Intent(context, MessageService.class);
                        context.startService(serviceIntent);
                    }
                }
            }
        }
    }
}
