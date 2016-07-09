package com.elgroup.foodbeat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.elgroup.foodbeat.Utils.Constants;
import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

    static SharedPreferences preference;
    public static int PUSH_NOTIFICATION = 0;
    NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    public static final int NOTIFICATION_ID = 1000;

    public GCMIntentService() {
        super(Constants.GOOGLE_SENDER_ID);
        preference = MyApplication.preference;
    }
    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i("Mydata", "reg id :::::: >>>>>>>>>>>>>>>>>>>>>> " + registrationId);
        SharedPreferences.Editor   editor = preference.edit();
        editor.putString("GCM_REGISTERED_ID", registrationId);
        editor.commit();
       // MyApplication.register(registrationId);
    }
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        //MyApplication.unregister("unregister");
    }
    @Override
    protected void onMessage(Context context, Intent intent) {

        System.out.println("data rcvd :" +intent.getExtras().toString());
        String message1 = intent.getExtras().getString("message");
        System.out.println(" gcm rcvd " +message1);
        String id = intent.getExtras().getString("id");
        String type = intent.getExtras().getString("type");
        //  displayNotification(context, message1);
        sendNotification(message1,id, type);
    }
    @Override
    protected void onDeletedMessages(Context context, int total) {
    }
    @Override
    public void onError(Context context, String errorId) {
    }
    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        return super.onRecoverableError(context, errorId);
    }

    private void sendNotification(String msg, String id, String type) {

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        System.out.println("noti msg " +msg);

        PendingIntent contentIntent = null;
        try{

            Intent notificationIntent;
            if(type.contains("Plan")) {

              //  notificationIntent = new Intent(this, PlanDetailsActivity.class);
              //  notificationIntent.putExtra("planId", id);
             //   notificationIntent.setAction("action.plan.noti");
            }
            else {
                notificationIntent = new Intent(this, HomeActivity.class);
            }
          //  contentIntent = PendingIntent.getActivity(this, 0,
                //    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_ONE_SHOT);








//
//            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
//                    this).setSmallIcon(R.drawable.push_icon)
//                    .setContentTitle("Slap")
//                    .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
//                    .setAutoCancel(true)
//                    .setSound(alarmSound)
//                    .setVibrate(new long[] { 1000})
//                    .setContentText(msg);


//
//            mBuilder.setContentIntent(contentIntent);
//            mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }
        catch (Exception e){

            System.out.println("GCM intentservice exception " +e);
        }
    }




    protected void displayNotification(Context context, String message) {

        NotificationCompat.Builder  mBuilder =
                new NotificationCompat.Builder(this);
        String title = context.getString(R.string.app_name);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setTicker("New Message Alert!");
       // mBuilder.setSmallIcon(R.drawable.app_logo);

        //mBuilder.setNumber(++MyApplication.numMessage);

        Intent resultIntent = new Intent(this, HomeActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(HomeActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(PUSH_NOTIFICATION, mBuilder.build());
    }
}

