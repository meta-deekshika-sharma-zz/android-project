package com.example.user15.intermediateapplication.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user15.intermediateapplication.dialog.CustomDialog;
import com.example.user15.intermediateapplication.services.MessageService;
import com.example.user15.intermediateapplication.R;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private CustomDialog customDialog;
    private Button openCameraBtn, readContactsBtn, sendSMSBtn, recordAudioBtn, notificationBtn, stopMusicPlayerBtn, customDrawableBtn, customViewBtn, customDialogBtn;
    private static final int OPEN_CAMERA = 0, READ_CONTACT = 1, SEND_SMS = 2, RECORD_AUDIO = 3, RECEIVE_SMS = 4;
    private String packageName = "com.example.user15.intermediateapplication";
    private boolean isLocationDenied, isCameraDenied, isContactDenied, isSmsDenied, isRecordDenied;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.constraintLayout);
        openCameraBtn = findViewById(R.id.openCameraBtn);
        readContactsBtn = findViewById(R.id.readContactsBtn);
        sendSMSBtn = findViewById(R.id.sendSMSBtn);
        recordAudioBtn = findViewById(R.id.recordAudioBtn);
        notificationBtn = findViewById(R.id.notificationBtn);
        stopMusicPlayerBtn = findViewById(R.id.stopMusicPlayerBtn);
        customDrawableBtn = findViewById(R.id.customDrawableBtn);
        customViewBtn = findViewById(R.id.customViewBtn);
        customDialogBtn = findViewById(R.id.customDialogBtn);

        openCameraBtn.setOnClickListener(this.onClickListener);
        readContactsBtn.setOnClickListener(this.onClickListener);
        sendSMSBtn.setOnClickListener(this.onClickListener);
        recordAudioBtn.setOnClickListener(this.onClickListener);
        notificationBtn.setOnClickListener(this.onClickListener);
        stopMusicPlayerBtn.setOnClickListener(this.onClickListener);
        customDrawableBtn.setOnClickListener(this.onClickListener);
        customViewBtn.setOnClickListener(this.onClickListener);
        customDialogBtn.setOnClickListener(this.onClickListener);

        isLocationDenied = false;
        isCameraDenied = false;
        isContactDenied = false;
        isSmsDenied = false;
        isRecordDenied = false;

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, RECEIVE_SMS);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.openCameraBtn:
                    openCamera();
                    break;

                case R.id.readContactsBtn:
                    readContact();
                    break;

                case R.id.sendSMSBtn:
                    sendSMS();
                    break;

                case R.id.recordAudioBtn:
                    recordAudio();
                    break;

                case R.id.notificationBtn:
                    notifyUser();
                    break;

                case R.id.stopMusicPlayerBtn:
                    StopMusicPlayer();
                    break;

                case R.id.customDrawableBtn:
                    startActivity(new Intent(MainActivity.this, CustomDrawableActivity.class));
                    break;

                case R.id.customViewBtn:
                    startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
                    break;

                case R.id.customDialogBtn:
                    customDialog = new CustomDialog();
                    //customDialog.show(getSupportFragmentManager(), "custom dialog");
                    customDialog.showDialog(MainActivity.this);
                    break;
            }
        }
    };

    private void StopMusicPlayer() {
        stopService(new Intent(MainActivity.this, MessageService.class));
    }

    private void notifyUser() {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,
                getString(R.string.channel_id))
                .setSmallIcon(R.drawable.ic_people_black_24dp)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setStyle(
                        new NotificationCompat.BigTextStyle()
                                .bigText(getString(R.string.notification_detail))
                );

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from
                (this);
        notificationManagerCompat.notify(1, notificationBuilder.build());
    }

    private void recordAudio() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) + ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                isLocationDenied = true;
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                    isRecordDenied = true;
                } else
                    isRecordDenied = false;
                Snackbar snackbar = Snackbar.make(constraintLayout, R.string.record_audio_permission, Snackbar.LENGTH_SHORT);
                snackbar.setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPermissions(RECORD_AUDIO);
                    }
                });
                snackbar.show();
            } else {
                if (!isLocationDenied || !isRecordDenied) {
                    isLocationDenied = true;
                    isRecordDenied = true;
                    requestPermissions(RECORD_AUDIO);
                } else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Permission Denied");
                    alertDialog.setMessage("Without this permission the app is enable to record the audio and Denied the location. Please allow the app to accept the permissions.");
                    alertDialog.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + packageName));
                            startActivity(intent);
                        }
                    });

                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.setCancelable(true);
                        }
                    });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }
            }
        }
    }

    private void sendSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) + ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

                isContactDenied = true;
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS))
                    isSmsDenied = true;
                else
                    isSmsDenied = false;
                Snackbar snackbar = Snackbar.make(constraintLayout, R.string.send_sms_permission, Snackbar.LENGTH_LONG);
                snackbar.setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPermissions(SEND_SMS);
                    }
                });
                snackbar.show();
            } else {
                if (!isContactDenied || !isSmsDenied) {
                    requestPermissions(SEND_SMS);
                    isContactDenied = true;
                    isSmsDenied = true;
                } else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Permission Denied");
                    alertDialog.setMessage("Without this permission the app is enable to send SMS and read the contacts. Please allow the app to accept the permissions.");
                    alertDialog.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + packageName));
                            startActivity(intent);
                        }
                    });

                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.setCancelable(true);
                        }
                    });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }
            }
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("7891617377", null, "I am in a meeting", null, null);
            Toast.makeText(this, "sent", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestPermissions(int requestCode) {

        switch (requestCode) {
            case OPEN_CAMERA:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION}, OPEN_CAMERA);
                break;
            case READ_CONTACT:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACT);
                break;
            case SEND_SMS:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_CONTACTS}, SEND_SMS);
                break;
            case RECORD_AUDIO:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION}, RECORD_AUDIO);
                break;
        }
    }

    private void readContact() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                isContactDenied = true;

                Snackbar snackbar = Snackbar.make(constraintLayout, R.string.read_contact_permission, Snackbar.LENGTH_LONG);
                snackbar.setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPermissions(READ_CONTACT);
                    }
                });
                snackbar.show();
            } else {
                if (isContactDenied == false) {
                    requestPermissions(READ_CONTACT);
                } else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Permission Denied");
                    alertDialog.setMessage("Without this permission the app is enable to read the contacts. Please allow the app to accept the permissions.");
                    alertDialog.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + packageName));
                            startActivity(intent);
                        }
                    });

                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.setCancelable(true);
                        }
                    });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }
            }
        } else
            startActivity(new Intent(Intent.ACTION_PICK));
    }

    private void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) + ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                isLocationDenied = true;
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
                    isCameraDenied = true;
                else
                    isCameraDenied = false;
                Snackbar snackbar = Snackbar.make(constraintLayout, R.string.camera_permission, Snackbar.LENGTH_LONG);
                snackbar.setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPermissions(OPEN_CAMERA);
                    }
                });
                snackbar.show();
            } else {
                if (!isCameraDenied || !isLocationDenied) {
                    requestPermissions(OPEN_CAMERA);
                    isCameraDenied = true;
                    isLocationDenied = true;
                } else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Permission Denied");
                    alertDialog.setMessage("Without this permission the app is enable to open camera, capture the image and Denied the location. Please allow the app to Accept the permissions.");
                    alertDialog.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + packageName));
                            startActivity(intent);
                        }
                    });

                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.setCancelable(true);
                        }
                    });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }
            }
        } else
            startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case OPEN_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                }
                break;

            case READ_CONTACT:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_PICK));
                }
                break;

            case SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("7891617377", null, "I am in a meeting", null, null);
                    Toast.makeText(this, "sent", Toast.LENGTH_SHORT).show();
                }
                break;

            case RECORD_AUDIO:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                }
                break;

            case RECEIVE_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
