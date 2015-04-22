package es.fraggel.lednotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.text.NumberFormat;


public class MainActivity extends ActionBarActivity {
    Handler mCleanLedHandler=null;
    private BroadcastReceiver screenOnListener
            ;
    private BroadcastReceiver screenOffListener;
    NotificationManager nm=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*NotificationManager notifMgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notifMgr.cancelAll();
        Notification notif = new Notification();
        notif.ledARGB = 0xffff0000;
        notif.ledOnMS = 99999;
        notif.ledOffMS = 0;
        notif.priority=Notification.PRIORITY_MAX;
        notif.flags |= Notification.FLAG_SHOW_LIGHTS;
        notifMgr.notify(1234, notif);*/
    }


}
