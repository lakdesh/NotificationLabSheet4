package com.terracodz.notification_labsheet_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTxt;
    Button btnReg;
    String name;

    //1. Notification Channel
    //2. Notification Builder
    //3. Notification Manager

    //for creating notification channel
    //1.create channel id
    public static final String CHANNEL_ID = "terra_codz";
    //2. create channel name
    public static final String CHANNEL_NAME = "Terra Codz";
    //3. create channel description
    public static final String CHANNEL_DESCRIPTION = "Terra Codz App Development";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create notification channel
        //check if the os greater than or equal to android oreo
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESCRIPTION);

        //in order to create the notification we need the NotificationManager class again
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        editTxt = (EditText) findViewById(R.id.editText);
        btnReg = (Button)findViewById(R.id.button);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, Notification.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent;
        pendingIntent =  PendingIntent.getActivity(this, 0, intent, 0);

    }

    //create the function for displaying the notification
    private void displayNotification(){
        name = editTxt.getText().toString();

        //2. using notification builder -> NotificationCompat
        NotificationCompat.Builder
                nBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My Notification")
                .setContentText("Hello "+name+"! Welcome to the MAD team")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                ;

        //3.Notification Manager
        NotificationManagerCompat nManager = NotificationManagerCompat.from(this);
        nManager.notify(0, nBuilder.build());
    }

}
