package com.iwalnexus.tsn.notificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NotficationHandler extends ContextWrapper {

    private NotificationManager manager;

    public static final String CHANNEL_ID ="1";
    public static final String CHANNEL_NAME = "Main";

    public NotficationHandler(Context base) {
        super(base);
        createChannel();
    }

    private void createChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            channel.setLightColor(Color.GREEN);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            getManager().createNotificationChannel(channel);

        }
    }

    public NotificationManager getManager() {

        if(manager == null){
            manager = (NotificationManager) getSystemService((Context.NOTIFICATION_SERVICE));
        }
        return  manager;
    }

    public Notification.Builder createNotification(String titulo, String msg, String Grupo){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return createNotificationWithChannel(titulo, msg, Grupo);
        } else {

           return createNotificationNoChannel(titulo,msg);
        }

    }


    private Notification.Builder createNotificationNoChannel(String t, String m) {


        return new Notification.Builder(getApplicationContext())
                .setContentTitle(t)
                .setContentText(m)
                .setContentIntent(getPendingIntent(t,m))
                .setSmallIcon(android.R.drawable.star_on)
                .setAutoCancel(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification.Builder createNotificationWithChannel(String t, String m, String Grupo) {


        return new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(t)
                .setContentText(m)
                .setContentIntent(getPendingIntent(t,m))
                .setGroup(Grupo)
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.star_on);
    }

    public PendingIntent getPendingIntent(String t, String m){

        int num = (int) System.currentTimeMillis();

        Intent intent = new Intent(getApplicationContext(), DetallesActivity.class);
        intent.putExtra("t", t);
        intent.putExtra("m", m);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),num, intent,PendingIntent.FLAG_CANCEL_CURRENT);

        return  pendingIntent;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void agruparNotificaciones(String Group, int idGrupo){
        Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.star_on)
                .setGroup(Group)
                .setGroupSummary(true)
                .build();

        getManager().notify(idGrupo, notification);
    }

}
