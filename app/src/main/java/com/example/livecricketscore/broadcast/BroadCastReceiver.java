package com.example.livecricketscore.broadcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.livecricketscore.R;
import com.example.livecricketscore.createchannel.CreateChannel;


public class BroadCastReceiver extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadCastReceiver(Context context){
        this.context = context;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noConnectivity;
        notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

            if (noConnectivity) {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                DisplayNotification1();


            }

            else {

                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                DisplayNotification2();

            }
        }

    }

    private void DisplayNotification2(){
        Notification notification = new NotificationCompat.Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.cricket)
                .setContentTitle("Live Cricket Score")
                .setContentText("There is  internet connection")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();

        notificationManagerCompat.notify(7, notification);
    }

    private void DisplayNotification1(){
        Notification notification = new NotificationCompat.Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.cricket)
                .setContentTitle("Live Cricket Score")
                .setContentText("There is no internet connection")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();

        notificationManagerCompat.notify(6, notification);
    }


}
